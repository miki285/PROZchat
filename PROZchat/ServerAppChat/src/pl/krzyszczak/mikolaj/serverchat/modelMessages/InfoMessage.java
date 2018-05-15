package pl.krzyszczak.mikolaj.serverchat.modelMessages;

/**
 * Klasa message kt�ra b�dzie tworzona je�eli wyst�pi b��d z dana
 */
public class InfoMessage extends ModelMessages
{
	/**
	 * wiadomo�� do przekazania
	 */
	private String infoMsg;

	/**
	 * konstruktor klasy InfoMessage
	 * 
	 * @param msg
	 *            - przesy�ana wiadomo��
	 */

	public InfoMessage(String msg)
	{
		this.infoMsg = msg;
	}

	/**
	 * Funkcja zwracaj�ca tre�� wiadomo�ci
	 * 
	 */
	public String getInfoMessage()
	{
		return this.infoMsg;
	}
}
