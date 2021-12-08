/*
 * 		OfficeCode.java
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
 * 		OfficeCode.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Aug 23, 2019
 */
package ar.com.adriancordoba.app.console.switching.subscribers;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class OfficeCode {
	private int code;

	/**
	 * 
	 */
	public OfficeCode() {
		super();
	}

	/**
	 * @param code
	 */
	public OfficeCode(int code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OfficeCode [code=" + code + "]";
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
