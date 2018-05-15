/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.appEvent;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * Klasa eventu zg�aszanego gdy u�ytkownik chce napisa� do nowego znajomego
 * na jej podstawie swtarzana jest nowa ramka a nast�pnie jest wy�wietlana
 * @author Miko�aj
 *
 */
public class OpenMessageWindowEvent extends ApplicationEvent
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserId toUserId;
	
	public OpenMessageWindowEvent(UserId toUserId)
	{
		this.toUserId=toUserId;
	}
	
	public UserId getToUserId()
	{
		return toUserId;
	}

}
