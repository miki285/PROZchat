package pl.krzyszczak.mikolaj.serverchat.modelMessages;

/**
 * Klasa message która bêdzie tworzona je¿eli wyst¹pi b³¹d z dana
 */
public class InfoMessage extends ModelMessages
{
	/**
	 * wiadomoœæ do przekazania
	 */
	private String infoMsg;

	/**
	 * konstruktor klasy InfoMessage
	 * 
	 * @param msg
	 *            - przesy³ana wiadomoœæ
	 */

	public InfoMessage(String msg)
	{
		this.infoMsg = msg;
	}

	/**
	 * Funkcja zwracaj¹ca treœæ wiadomoœci
	 * 
	 */
	public String getInfoMessage()
	{
		return this.infoMsg;
	}
}
