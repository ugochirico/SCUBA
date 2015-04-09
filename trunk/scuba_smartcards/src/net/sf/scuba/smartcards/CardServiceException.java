/*
 * This file is part of the SCUBA smart card framework.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * Copyright (C) 2009 - 2015  The SCUBA team.
 *
 * $Id$
 */

package net.sf.scuba.smartcards;

/**
 * CardServiceExceptions are used to signal error Response APDUs , ie responses
 * different from 0x9000, but also low level errors.
 * 
 * @author erikpoll
 * 
 * @version $Revision$
 */
public class CardServiceException extends Exception {

	private static final long serialVersionUID = 4489156194716970879L;

	public static final int SW_NONE = -1;
	
	/**
	 * The status word that caused this exception, or -1 if not known or recorded.
	 */
	private int sw = SW_NONE;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/**
	 * Creates a CardServiceException.
	 * 
	 * @param msg a message
	 */
	public CardServiceException(String msg) {
		super(msg);
	}

	/**
	 * Creates a CardServiceException with a status word.
	 * 
	 * @param msg a message
	 * @param sw the status word that caused this CardServiceException
	 */
	public CardServiceException(String msg, int sw) {
		super(msg);
		this.sw = sw;
	}
	
	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		if (sw == -1) {
			return super.getMessage();
		} else {
			return super.getMessage() + " (SW = 0x" + Integer.toHexString(sw) + ")";
		}
	}

	/**
	 * Gets the status word.
	 * 
	 * @return the status word that caused this exception
	 */
	public int getSW() {
		return sw;
	}
}
