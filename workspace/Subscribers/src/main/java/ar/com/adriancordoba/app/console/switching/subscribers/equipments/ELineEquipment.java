/*
 * 		ELineEquipment.java
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
 * 		ELineEquipment.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 23, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers.equipments;

import java.util.Arrays;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class ELineEquipment extends Equipment {
	private char[] spce;
	private char highway;
	private char subhighway;
	private char[] group;
	private char sw;
	private char level;

	/**
	 * @throws Exception
	 * 
	 */
	public ELineEquipment(String equipment) throws Exception {
		super();
		if (equipment.length() == 8) {
			spce = equipment.substring(0, 2).toCharArray();
			highway = equipment.charAt(2);
			subhighway = equipment.charAt(3);
			group = equipment.substring(4, 6).toCharArray();
			sw = equipment.charAt(6);
			level = equipment.charAt(7);
		} else
			throw new Exception("Length of NEAX61E line equipment error: " + equipment + ".");
	}

	/**
	 * 
	 */
	public ELineEquipment() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ELineEquipment [spce=" + Arrays.toString(spce) + ", highway=" + highway + ", subhighway=" + subhighway + ", group=" + Arrays.toString(group) + ", sw=" + sw + ", level=" + level + "]";
	}

	/**
	 * @return the spce
	 */
	public char[] getSpce() {
		return spce;
	}

	/**
	 * @param spce the spce to set
	 */
	public void setSpce(char[] spce) {
		this.spce = spce;
	}

	/**
	 * @return the highway
	 */
	public char getHighway() {
		return highway;
	}

	/**
	 * @param highway the highway to set
	 */
	public void setHighway(char highway) {
		this.highway = highway;
	}

	/**
	 * @return the subhighway
	 */
	public char getSubhighway() {
		return subhighway;
	}

	/**
	 * @param subhighway the subhighway to set
	 */
	public void setSubhighway(char subhighway) {
		this.subhighway = subhighway;
	}

	/**
	 * @return the group
	 */
	public char[] getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(char[] group) {
		this.group = group;
	}

	/**
	 * @return the sw
	 */
	public char getSw() {
		return sw;
	}

	/**
	 * @param sw the sw to set
	 */
	public void setSw(char sw) {
		this.sw = sw;
	}

	/**
	 * @return the level
	 */
	public char getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(char level) {
		this.level = level;
	}
}
