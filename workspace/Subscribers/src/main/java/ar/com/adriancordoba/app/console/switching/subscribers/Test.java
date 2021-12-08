/*
 * 		Test.java
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
 * 		Test.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 26, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.app.console.commonservices.configuration.Configuration;
import ar.com.adriancordoba.app.console.commonservices.logging.Logging;
import ar.com.adriancordoba.app.console.switching.subscribers.discoverers.SubscribersScanner;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Test {
	private static final Logger logger;
	
	static {
		Logging.configure();
		logger = LogManager.getLogger(SubscribersScanner.class);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SubscribersScanner scanner = new SubscribersScanner();
		List<Subscriber> subscribersList = scanner.getSubscribersList();
		BufferedWriter writer = new BufferedWriter(new FileWriter("/home/adrian/Temporary/result.txt"));
		
		for(Subscriber subscriber : subscribersList) {
			writer.write(subscriber.toString());
			writer.newLine();
		}
		writer.close();
	}
}
