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
 * $Id: APDUWrapper.java 255 2016-01-20 07:37:59Z martijno $
 */

package net.sf.scuba.smartcards;

import java.io.Serializable;

/**
 * Card side APDU wrapper.
 * 
 * @author Martijn Oostdijk (martijn.oostdijk@gmail.com)
 * 
 * @version $Revision: $
 * 
 * @since 0.0.10
 */
public interface ReverseAPDUWrapper extends Serializable {
  
  /**
   * Unwraps a Command APDU received from the terminal.
   * 
   * @param wrappedCommandAPDU a wrapped Command APDU to be unwrapped
   * 
   * @return the unwrapped Command APDU
   */
  CommandAPDU unwrap(CommandAPDU wrappedCommandAPDU);

  /**
   * Wraps a Response APDU to be sent back to the terminal.
   * 
   * @param responseAPDU a Response APDU
   * 
   * @return a wrapped Response APDU
   */
  ResponseAPDU wrap(ResponseAPDU responseAPDU); 
}
