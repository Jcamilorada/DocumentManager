package domain.exception;


/**
 * @author Jairo Andres Velasco
 *
 * Indicates that a non well known internal error had ocurred.
 * It may happen when an error occurs within an underlying component
 * and its details should not be exposed or they are unknown.
 */
public class UnspecifiedInternalException extends Exception
{
	private static final String baseMessage = "Unspecified internal exception";

	public UnspecifiedInternalException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public UnspecifiedInternalException()
	{
		super(baseMessage);
	}

	public UnspecifiedInternalException(Throwable cause)
	{
		super(baseMessage, cause);
	}

	public UnspecifiedInternalException(String msg)
	{
		super(msg);
	}
}

