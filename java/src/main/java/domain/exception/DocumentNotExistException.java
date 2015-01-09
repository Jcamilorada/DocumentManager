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
	private static final String baseMessage = "Document not found";

	public DocumentNotExistException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public DocumentNotExistException()
	{
		super(baseMessage);
	}

	public DocumentNotExistException(Throwable cause)
	{
		super(baseMessage, cause);
	}

	public DocumentNotExistException(String msg)
	{
		super(msg);
	}
}
