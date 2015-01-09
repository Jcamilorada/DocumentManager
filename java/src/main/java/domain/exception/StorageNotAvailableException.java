
/*
 * StorageNotAvailableException.java is part of Document Manager (c) 2015.
 *
 * Document Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Document Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package domain.exception;

import java.io.IOException;

/**
 * @author Jairo Andres Velasco
 *
 * Idicates that storage component could not be accessed to complete the operation or
 * it is not reliable.
 * It may happen due to network or hardware failure.
 */
public class StorageNotAvailableException extends IOException
{
	private static final String baseMessage = "Storage is not available";

	public StorageNotAvailableException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public StorageNotAvailableException()
	{
		super(baseMessage);
	}

	public StorageNotAvailableException(Throwable cause)
	{
		super(baseMessage, cause);
	}

	public StorageNotAvailableException(String msg)
	{
		super(msg);
	}
}
