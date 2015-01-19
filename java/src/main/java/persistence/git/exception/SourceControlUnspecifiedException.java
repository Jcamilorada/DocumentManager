/*
 * SourceControlUnspecifiedException.java is part of Document Manager (c) 2015.
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

package persistence.git.exception;

/**
 * @author Jairo Andres Velasco
 *
 * Indicates that a non well known source control error had ocurred.
 * It may happen when an error occurs within an underlying component
 * and its details should not be exposed or they are unknown.
 */
public class SourceControlUnspecifiedException extends Exception
{
    private static final String BASE_MESSAGE = "Unspecified source control exception";

    public SourceControlUnspecifiedException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public SourceControlUnspecifiedException()
    {
        super(BASE_MESSAGE);
    }

    public SourceControlUnspecifiedException(Throwable cause)
    {
        super(BASE_MESSAGE, cause);
    }

    public SourceControlUnspecifiedException(String msg)
    {
        super(msg);
    }
}

