/*
 * 		SigmaLineEquipment.java
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
 * 		SigmaLineEquipment.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 23, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers.equipments;

import java.util.Arrays;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class SigmaLineEquipment extends Equipment {
	private char[] timeSwitch;
	private char[] kHighway;
	private char[] pHighway;
	private char row;
	private char[] column;

	/**
	 * @throws Exception
	 * 
	 */
	public SigmaLineEquipment(String equipment) throws Exception {
		super();
		if (equipment.length() == 9) {
			timeSwitch = equipment.substring(0, 2).toCharArray();
			kHighway = equipment.substring(2, 4).toCharArray();
			pHighway = equipment.substring(4, 6).toCharArray();
			row = equipment.charAt(6);
			column = equipment.substring(7).toCharArray();
		} else
			throw new Exception("Length of NEAX61Σ line equipment error: " + equipment + ".");
	}

	/**
	 * 
	 */
	public SigmaLineEquipment() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SigmaLineEquipment [timeSwitch=" + Arrays.toString(timeSwitch) + ", kHighway=" + Arrays.toString(kHighway) + ", pHighway=" + Arrays.toString(pHighway) + ", row=" + row + ", column=" + Arrays.toString(column) + "]";
	}

	/**
	 * @return the timeSwitch
	 */
	public char[] getTimeSwitch() {
		return timeSwitch;
	}

	/**
	 * @param timeSwitch the timeSwitch to set
	 */
	public void setTimeSwitch(char[] timeSwitch) {
		this.timeSwitch = timeSwitch;
	}

	/**
	 * @return the kHighway
	 */
	public char[] getkHighway() {
		return kHighway;
	}

	/**
	 * @param kHighway the kHighway to set
	 */
	public void setkHighway(char[] kHighway) {
		this.kHighway = kHighway;
	}

	/**
	 * @return the pHighway
	 */
	public char[] getpHighway() {
		return pHighway;
	}

	/**
	 * @param pHighway the pHighway to set
	 */
	public void setpHighway(char[] pHighway) {
		this.pHighway = pHighway;
	}

	/**
	 * @return the row
	 */
	public char getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(char row) {
		this.row = row;
	}

	/**
	 * @return the column
	 */
	public char[] getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(char[] column) {
		this.column = column;
	}
}
