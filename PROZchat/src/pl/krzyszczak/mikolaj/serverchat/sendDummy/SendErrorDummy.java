/**
 * 
 */
package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;

/**
 * Klasa makiety wysy�aj�cej b��dy do klienta
 * 
 * @author Miko�aj
 *
 */
public class SendErrorDummy extends SendDummy implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1177731724934887286L;
	/** tre�� b��du */
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
