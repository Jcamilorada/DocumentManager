/*
 * InvalidAPIUsageException.java is part of Document Manager (c) 2015.
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
 * @author Juan Camilo Rada
 *
 * Indicates that a domain operation was performed with invalid parameters.
 */
public class InvalidAPIUsageException extends Exception
{
    private static final String BASE_MESSAGE = "Invalid API usage";

    public InvalidAPIUsageException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public InvalidAPIUsageException()
    {
        super(BASE_MESSAGE);
    }

    public InvalidAPIUsageException(Throwable cause)
    {
        super(BASE_MESSAGE, cause);
    }

    public InvalidAPIUsageException(String msg)
    {
        super(msg);
    }
}
