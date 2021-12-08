/*
 * 		SigmaELUEquipment.java
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
 * 		SigmaELUEquipment.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 23, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers.equipments;

import java.util.Arrays;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class SigmaELUEquipment extends Equipment {
	private String anw;
	private char[] l3addr;

	/**
	 * @param anw
	 * @param l3addr
	 * @throws Exception
	 */
	public SigmaELUEquipment(String anw, String l3addr) throws Exception {
		super();
		this.anw = anw;
		if (l3addr.length() == 5)
			this.l3addr = l3addr.toCharArray();
		else
			throw new Exception("Length of Level 3 Address error: " + l3addr + ".");

	}

	/**
	 * 
	 */
	public SigmaELUEquipment() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SigmaELUEquipment [anw=" + anw + ", l3addr=" + Arrays.toString(l3addr) + "]";
	}

	/**
	 * @return the anw
	 */
	public String getAnw() {
		return anw;
	}

	/**
	 * @param anw the anw to set
	 */
	public void setAnw(String anw) {
		this.anw = anw;
	}

	/**
	 * @return the l3addr
	 */
	public char[] getL3addr() {
		return l3addr;
	}

	/**
	 * @param l3addr the l3addr to set
	 */
	public void setL3addr(char[] l3addr) {
		this.l3addr = l3addr;
	}
}
