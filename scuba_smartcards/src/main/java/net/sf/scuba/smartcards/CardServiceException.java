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
      return super.getMessage() + " (SW = 0x" + Integer.toHexString(sw).toUpperCase() + ": " + statusWordToString((short)sw) + ")";
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

  private static String statusWordToString(short sw) {
    switch(sw) {
      case ISO7816.SW_END_OF_FILE: return "END OF FILE";
      case ISO7816.SW_LESS_DATA_RESPONDED_THAN_REQUESTED: return "LESS DATA RESPONDED THAN REQUESTED";
      case ISO7816.SW_WRONG_LENGTH: return "WRONG LENGTH";
      case ISO7816.SW_LOGICAL_CHANNEL_NOT_SUPPORTED: return "LOGICAL CHANNEL NOT SUPPORTED";
      case ISO7816.SW_SECURE_MESSAGING_NOT_SUPPORTED : return "SECURE MESSAGING NOT SUPPORTED";
      case ISO7816.SW_LAST_COMMAND_EXPECTED: return "LAST COMMAND EXPECTED";
      case ISO7816.SW_SECURITY_STATUS_NOT_SATISFIED: return "SECURITY STATUS NOT SATISFIED";
      case ISO7816.SW_FILE_INVALID: return "FILE INVALID";
      case ISO7816.SW_DATA_INVALID: return "DATA INVALID";
      case ISO7816.SW_CONDITIONS_NOT_SATISFIED: return "CONDITIONS NOT SATISFIED";
      case ISO7816.SW_COMMAND_NOT_ALLOWED: return "COMMAND NOT ALLOWED";
      case ISO7816.SW_EXPECTED_SM_DATA_OBJECTS_MISSING: return "EXPECTED SM DATA OBJECTS MISSING";
      case ISO7816.SW_SM_DATA_OBJECTS_INCORRECT: return "SM DATA OBJECTS INCORRECT";
      case ISO7816.SW_APPLET_SELECT_FAILED: return "APPLET SELECT FAILED";
      case ISO7816.SW_KEY_USAGE_ERROR: return "KEY USAGE ERROR";
      case ISO7816.SW_WRONG_DATA:
        /* case ISO7816.SW_FILEHEADER_INCONSISTENT: */
        return "WRONG DATA or FILEHEADER INCONSISTENT";
      case ISO7816.SW_FUNC_NOT_SUPPORTED: return "FUNC NOT SUPPORTED";
      case ISO7816.SW_FILE_NOT_FOUND: return "FILE NOT FOUND";
      case ISO7816.SW_RECORD_NOT_FOUND: return "RECORD NOT FOUND";
      case ISO7816.SW_OUT_OF_MEMORY:
        /* case ISO7816.SW_FILE_FULL: */
        return "OUT OF MEMORY or FILE FULL";
      case ISO7816.SW_INCORRECT_P1P2: return "INCORRECT P1P2";
      case ISO7816.SW_KEY_NOT_FOUND: return "KEY NOT FOUND";
      case ISO7816.SW_WRONG_P1P2: return "WRONG P1P2";
      case ISO7816.SW_INS_NOT_SUPPORTED: return "INS NOT SUPPORTED";
      case ISO7816.SW_CLA_NOT_SUPPORTED: return "CLA NOT SUPPORTED";
      case ISO7816.SW_UNKNOWN: return "UNKNOWN";
      case ISO7816.SW_CARD_TERMINATED: return "CARD TERMINATED";
      case ISO7816.SW_NO_ERROR: return "NO ERROR";
    }

    if ((sw & 0xFF00) == ISO7816.SW_BYTES_REMAINING_00) {
      return "BYTES REMAINING " + Integer.toString(sw & 0xFF);
    }

    if ((sw & 0xFF00) == ISO7816.SW_CORRECT_LENGTH_00) {
      return "CORRECT LENGTH " + Integer.toString(sw & 0xFF);
    }

    if ((sw & 0xFFF0) == ISO7816.SW_NON_VOLATILE_MEMORY_CHANGED_COUNTER_0) {
      return "NON VOLATILE MEMORY CHANGED COUNT " + Integer.toString(sw & 0xF);
    }

    return "Unknown";
  }
}
