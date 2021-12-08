/*
 * 		FilesHandler.java
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
 * 		FilesHandler.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 25, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers.utilities.fileshandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class FilesHandler {
	private File inputFile;

	private static final Logger logger = LogManager.getLogger(FilesHandler.class);;

	/**
	 * @param inputDirectory
	 * @param outputFilename
	 * @throws Exception
	 */
	public FilesHandler(String inputDirectory) throws Exception {
		try {
			inputFile = getDirectory(inputDirectory);
		} catch (Exception e) {
			logger.error("File problem.");
			throw new Exception("File problem.");
		}
		logger.info("Search files from " + inputDirectory + " directory.");
	}

	public List<File> getFilesList() {
		List<File> filesList = new ArrayList<>();
		getFiles(inputFile, filesList);
		return filesList;
	}

	public static File getFile(String filename) throws Exception {
		File file = null;
		if (filename.endsWith("/") || filename.endsWith(".")) {
			logger.error("Invalid filename: " + filename + ".");
			throw new Exception();
		} else {
			file = new File(filename);
			if (file.exists()) {
				logger.error("Invalid filename: " + filename + " already exists.");
				throw new Exception();
			}
		}
		return file;
	}

	/**
	 * 
	 * @param directoryPath
	 * @return
	 * @throws Exception
	 */
	private File getDirectory(String directoryPath) throws Exception {
		File directory = new File(directoryPath);

		if (!directory.isDirectory()) {
			logger.error("Invalid directory path: " + directoryPath + ".");
			throw new Exception("Invalid directory path.");
		}
		return directory;
	}

	/**
	 * @param directory
	 * @param filesList
	 */
	private void getFiles(File directory, List<File> filesList) {
		String[] tempList = directory.list();
		for (int i = 0; i < tempList.length; i++) {
			File file = new File(directory.getAbsolutePath() + "/" + tempList[i]);
			if (file.isFile())
				filesList.add(file);
			else {
				getFiles(file, filesList);
			}
		}
	}
}
