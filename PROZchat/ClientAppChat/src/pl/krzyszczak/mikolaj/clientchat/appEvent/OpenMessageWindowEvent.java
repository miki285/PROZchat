/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.appEvent;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * Klasa eventu zgłaszanego gdy użytkownik chce napisać do nowego znajomego
 * na jej podstawie swtarzana jest nowa ramka a następnie jest wyświetlana
 * @author Mikołaj
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
