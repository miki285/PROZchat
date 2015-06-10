/**
 * 
 */
package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;

/**
 * Klasa makiety wysy³aj¹cej b³êdy do klienta
 * 
 * @author Miko³aj
 *
 */
public class SendErrorDummy extends SendDummy implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1177731724934887286L;
	/** treœæ b³êdu */
	private final String errorMessage;
	/**
	 * Konstruktor klasy ErrorDummy
	 * @param errorMessage
	 */
	public SendErrorDummy(final String errorMessage)
	{
		this.errorMessage=errorMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	
}
