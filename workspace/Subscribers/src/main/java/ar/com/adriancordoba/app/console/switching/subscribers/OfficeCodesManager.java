/*
 * 		OfficeCodesManager.java
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
 * 		OfficeCodesManager.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 27, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.adriancordoba.app.console.switching.subscribers.utilities.fileshandler.FileProcessor;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class OfficeCodesManager {
	private static List<OfficeCode> officeCodesList = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(OfficeCodesManager.class);
	
	public static OfficeCode getOfficeCode(String string) throws Exception {
		OfficeCode officeCode = null;
		int ofc = 0;
		try {
			ofc = Integer.parseInt(string);
		}catch(NumberFormatException exception) {
			logger.error("Office code bad format: " + string + ".", exception);
			throw new Exception("Office code bad format.");
		}
		
		for(OfficeCode temp : officeCodesList) {
			if(temp.getCode() == ofc) {
				officeCode = temp;
				break;
			}
		}
		
		if(officeCode == null) {
			officeCode = new OfficeCode(ofc);
			officeCodesList.add(officeCode);
		}
		
		return officeCode;
	}
}
