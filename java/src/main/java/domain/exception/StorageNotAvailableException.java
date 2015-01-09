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
