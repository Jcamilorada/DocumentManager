/*
 * DocumentNotExistException.java is part of Document Manager (c) 2015.
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

/**
 * @author Jairo Andres Velasco
 *
 * Indicates that a document being accesed is not found using the provided identity.
 * It may happen when a document do not exist due a deletion, when client try
 * to access a document using an invalid document identity or when a broken cross reference
 * to a document is being resolved.
 * This exception should not be raisen when document is not found due to bad search criteria is used
 * or storage and network medium is disconnected.
 */
public class DocumentNotExistException extends IllegalStateException
{
	private static final String BASE_MESSAGE = "Document not found";

	public DocumentNotExistException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public DocumentNotExistException()
	{
		super(BASE_MESSAGE);
	}

	public DocumentNotExistException(Throwable cause)
	{
		super(BASE_MESSAGE, cause);
	}

	public DocumentNotExistException(String msg)
	{
		super(msg);
	}
}
