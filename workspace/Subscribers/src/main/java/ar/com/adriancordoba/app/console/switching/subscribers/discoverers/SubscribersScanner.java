/*
 * 		SubscribersScanner.java
 *   Copyright (C) 2019  Adrián E. Córdoba [software.asia@gmail.com]
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 		Utilities.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 25, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers.discoverers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.app.console.commonservices.configuration.Configuration;
import ar.com.adriancordoba.app.console.switching.subscribers.OfficeCode;
import ar.com.adriancordoba.app.console.switching.subscribers.OfficeCodesManager;
import ar.com.adriancordoba.app.console.switching.subscribers.Subscriber;
import ar.com.adriancordoba.app.console.switching.subscribers.Technology;
import ar.com.adriancordoba.app.console.switching.subscribers.equipments.ELineEquipment;
import ar.com.adriancordoba.app.console.switching.subscribers.equipments.SigmaELUEquipment;
import ar.com.adriancordoba.app.console.switching.subscribers.equipments.SigmaLineEquipment;
import ar.com.adriancordoba.app.console.switching.subscribers.utilities.fileshandler.FileProcessor;
import ar.com.adriancordoba.app.console.switching.subscribers.utilities.fileshandler.FilesHandler;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class SubscribersScanner {
	private List<Subscriber> subscribersList;
	private static CompositeConfiguration configuration;

	private static final Logger logger = LogManager.getLogger(SubscribersScanner.class);

	public SubscribersScanner() throws Exception {
		subscribersList = new ArrayList<>();
		FileProcessor fileProcessor = null;
		try {
			configuration = Configuration.getInstance("settings.xml");
		} catch (ConfigurationException ex) {
			logger.fatal("Cannot load configuration file.", ex);
			throw new Exception("Cannot load configuration file.");
		}

		FilesHandler filesHandler = new FilesHandler(configuration.getString("subscribers.files-handler.input-directory"));
		List<File> filesList = filesHandler.getFilesList();
		for (File file : filesList) {
			int subscribersCounter = 0;
			fileProcessor = new FileProcessor(file);
			List<String> subscriberStringsList = fileProcessor.getNextSubscriberStringsList();
			Subscriber subscriber = null;
			while (subscriberStringsList != null) {
				if (fileProcessor.getTechnology() == Technology.NEAX61E)
					subscriber = getNeax61ESubscriber(subscriberStringsList);
				else if (fileProcessor.getTechnology() == Technology.NEAX61Σ)
					subscriber = getNeax61SigmaSubscriber(subscriberStringsList);

				if (subscriber.getEquipment() != null) {
					int index = getSubscriberIndexFromList(subscriber.getOfficeCode(), subscriber.getNumber());
					if (index >= 0) {
						Subscriber previousSubscriber = subscribersList.get(index);
						if (previousSubscriber.getTechnology() == Technology.NEAX61E && subscriber.getTechnology() == Technology.NEAX61Σ) {
							subscribersList.set(index, subscriber);
							logger.warn("Subscriber change: " + subscriber.toString());
						}
					} else {
						subscribersList.add(subscriber);
						subscribersCounter++;
					}
				} else
					logger.warn("Subscriber " + subscriber.getPhoneNumber() + " with no equipment.");

				subscriberStringsList = fileProcessor.getNextSubscriberStringsList();
			}
			logger.info(subscribersCounter + " subscribers processed from " + file.getName() + " file.");
		}
	}

	private Subscriber getNeax61ESubscriber(List<String> stringsList) throws Exception {
		Subscriber subscriber = new Subscriber();
		char number[] = null;
		subscriber.setTechnology(Technology.NEAX61E);
		for (String string : stringsList) {
			if (string.contains("# OFC=")) {
				OfficeCode officeCode = OfficeCodesManager.getOfficeCode(string.substring(14, string.indexOf(' ', 14)));
				subscriber.setOfficeCode(officeCode);
			} else if (string.contains("EL=")) {
				number = new char[4];
				for (int i = 0; i <= 3; i++)
					number[i] = string.charAt(i + 4);
				subscriber.setNumber(number);
				String equipmentString = string.substring(16, string.indexOf(' ', 16));
				if (!equipmentString.equals("********"))
					subscriber.setEquipment(new ELineEquipment(equipmentString));
			}
		}
		return subscriber;
	}

	private Subscriber getNeax61SigmaSubscriber(List<String> stringsList) throws Exception {
		Subscriber subscriber = new Subscriber();
		char number[] = null;
		char l3addr[] = null;
		subscriber.setTechnology(Technology.NEAX61Σ);
		for (String string : stringsList) {
			if (string.startsWith("n=")) {
				OfficeCode officeCode = OfficeCodesManager.getOfficeCode(string.substring(2, string.indexOf(' ', 2) - 4));
				subscriber.setOfficeCode(officeCode);
				number = new char[4];
				for (int i = 0; i <= 3; i++)
					number[i] = string.charAt(string.indexOf(' ') - 4 + i);
				subscriber.setNumber(number);
			} else if (string.startsWith("el=")) {
				SigmaLineEquipment equipment = new SigmaLineEquipment(string.substring(3));
				subscriber.setEquipment(equipment);
			} else if (string.startsWith("anw=")) {
				SigmaELUEquipment equipment = new SigmaELUEquipment();
				equipment.setAnw(string.substring(4, string.indexOf(' ')));
				l3addr = new char[5];
				for (int i = 0; i < 5; i++)
					l3addr[i] = string.charAt(22 + i);
				equipment.setL3addr(l3addr);
				subscriber.setEquipment(equipment);
			}
		}
		return subscriber;
	}

	private int getSubscriberIndexFromList(OfficeCode officeCode, char[] number) {
		int index = -1;
		for (int i = 0; i < subscribersList.size(); i++) {
			Subscriber temp = subscribersList.get(i);
			if ((officeCode.getCode() == temp.getOfficeCode().getCode()) && Arrays.equals(number, temp.getNumber())) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * @return the subscribersList
	 */
	public List<Subscriber> getSubscribersList() {
		return subscribersList;
	}
}
