/*
 * 		FileProcessor.java
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
 * 		FileProcessor.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 26, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers.utilities.fileshandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.app.console.switching.subscribers.Technology;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class FileProcessor {
	private File file;
	private BufferedReader bufferedReader;
	private Technology technology;
	private String ofc;
	private String line;

	private static final Logger logger = LogManager.getLogger(FileProcessor.class);

	/**
	 * @param file
	 * @throws Exception
	 */
	public FileProcessor(File file) throws Exception {
		super();
		this.file = file;
		try {
			this.bufferedReader = new BufferedReader(new FileReader(file));
			setTechnology();
		} catch (FileNotFoundException e) {
			logger.error("Cannot open " + file.getName() + " file to read.", e);
			throw new Exception("Cannot open file to read.");
		}
		logger.info("Processing " + file.getName() + " file. (" + technology.toString() + " technology)");
	}

	private void setTechnology() throws Exception {
		try {
			line = bufferedReader.readLine();
		} catch (IOException e) {
			logger.error("Cannot read from " + file.getName() + " file", e);
			throw new Exception("Cannot read line from file.");
		}
		while (line != null) {
			if (line.contains("...PSD ## LISTA DE DATOS DE ABONADO ##")) {
				technology = Technology.NEAX61E;
				return;
			} else if (line.contains("### view subd start ###")) {
				technology = Technology.NEAX61Σ;
				return;
			}
			try {
				line = bufferedReader.readLine();
			} catch (IOException e) {
				logger.error("Cannot read from " + file.getName() + " file.", e);
				throw new Exception("Cannot read line from file.");
			}
		}
		logger.error("EOF found in " + file.getName() + " file before technology can be determined.");
		throw new Exception("Technology cannot be determined.");
	}

	public List<String> getNextSubscriberStringsList() throws Exception {
		List<String> subscriberStringsList = null;
		if (technology == Technology.NEAX61E)
			subscriberStringsList = getNextNeax61EStringsList();
		else if (technology == Technology.NEAX61Σ)
			subscriberStringsList = getNextNeax61SigmaStringsList();
		return subscriberStringsList;
	}

	private List<String> getNextNeax61EStringsList() throws Exception {
		List<String> neax61ESubscriberStringsList = null;

		try {
			line = bufferedReader.readLine();
		} catch (IOException e) {
			logger.error("Cannot read from " + file.getName() + " file", e);
			throw new Exception("Cannot read line from file.");
		}

		while (line != null) {
			if (!line.isEmpty()) {
				if (line.contains("# OFC="))
					ofc = line;
				else if (line.contains("EL=") && !line.contains("N=")) {
					neax61ESubscriberStringsList = new ArrayList<>();
					neax61ESubscriberStringsList.add(ofc);
					neax61ESubscriberStringsList.add(line);
					break;
				}
			}

			try {
				line = bufferedReader.readLine();
			} catch (IOException e) {
				logger.error("Cannot read from " + file.getName() + " file", e);
				throw new Exception("Cannot read line from file.");
			}
		}
		if(line == null)
			bufferedReader.close();

		return neax61ESubscriberStringsList;
	}

	private List<String> getNextNeax61SigmaStringsList() throws Exception {
		List<String> neax61SigmaSubscriberStringsList = null;

		try {
			line = bufferedReader.readLine();
		} catch (IOException e) {
			logger.error("Cannot read from " + file.getName() + " file", e);
			throw new Exception("Cannot read line from file.");
		}

		while (line != null) {
			if (!line.isEmpty()) {
				if (line.startsWith("n=") && !line.contains("st=")) {
					neax61SigmaSubscriberStringsList = new ArrayList<>();
					neax61SigmaSubscriberStringsList.add(line);
				} else if (neax61SigmaSubscriberStringsList != null && (line.startsWith("# basic information #") || line.startsWith("//total=")))
					break;
				else if (neax61SigmaSubscriberStringsList != null)
					neax61SigmaSubscriberStringsList.add(line);
			}

			try {
				line = bufferedReader.readLine();
			} catch (IOException e) {
				logger.error("Cannot read from " + file.getName() + " file", e);
				throw new Exception("Cannot read line from file.");
			}
		}
		if(line == null)
			bufferedReader.close();

		return neax61SigmaSubscriberStringsList;
	}

	/**
	 * @return the technology
	 */
	public Technology getTechnology() {
		return technology;
	}

	/**
	 * @param technology the technology to set
	 */
	public void setTechnology(Technology technology) {
		this.technology = technology;
	}
}
