/*
 * 		Subscriber.java
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
 * 		Subscriber.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 23, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers;

import java.util.Arrays;
import java.util.List;

import ar.com.adriancordoba.app.console.switching.subscribers.data.Restrictions;
import ar.com.adriancordoba.app.console.switching.subscribers.equipments.Equipment;
import ar.com.adriancordoba.app.console.switching.subscribers.services.Service;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Subscriber {
	private OfficeCode officeCode;
	private char[] number;
	private Equipment equipment;
	private Restrictions restrictions;
	private List<Service> servicesList;
	private Technology technology;

	/**
	 * @param officeCode
	 * @param number
	 * @param equipment
	 * @param restrictions
	 * @param servicesList
	 */
	public Subscriber(OfficeCode officeCode, char[] number, Equipment equipment, Restrictions restrictions, List<Service> servicesList) {
		this.officeCode = officeCode;
		this.number = number;
		this.equipment = equipment;
		this.restrictions = restrictions;
		this.servicesList = servicesList;
	}

	/**
	 * 
	 */
	public Subscriber() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Subscriber [officeCode=" + officeCode + ", number=" + Arrays.toString(number) + ", equipment=" + equipment + ", restrictions=" + restrictions + ", servicesList=" + servicesList + "]";
	}

	public String getPhoneNumber() {
		StringBuilder phoneNumber = new StringBuilder(String.valueOf(officeCode.getCode()));
		phoneNumber.append(number);
		return phoneNumber.toString();
	}
	
	/**
	 * @return the officeCode
	 */
	public OfficeCode getOfficeCode() {
		return officeCode;
	}

	/**
	 * @param officeCode the officeCode to set
	 */
	public void setOfficeCode(OfficeCode officeCode) {
		this.officeCode = officeCode;
	}

	/**
	 * @return the number
	 */
	public char[] getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(char[] number) {
		this.number = number;
	}

	/**
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	/**
	 * @return the restrictions
	 */
	public Restrictions getRestrictions() {
		return restrictions;
	}

	/**
	 * @param restrictions the restrictions to set
	 */
	public void setRestrictions(Restrictions restrictions) {
		this.restrictions = restrictions;
	}

	/**
	 * @return the servicesList
	 */
	public List<Service> getServicesList() {
		return servicesList;
	}

	/**
	 * @param servicesList the servicesList to set
	 */
	public void setServicesList(List<Service> servicesList) {
		this.servicesList = servicesList;
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
