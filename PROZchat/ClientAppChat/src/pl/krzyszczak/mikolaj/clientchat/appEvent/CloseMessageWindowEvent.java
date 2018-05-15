/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.appEvent;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * @author Miko�aj
 *
 */
public class CloseMessageWindowEvent extends ApplicationEvent
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** UserId u�ytkownika kt�remu zamkni�to okno */
	private UserId userId;

	public CloseMessageWindowEvent(UserId userId)
	{
		this.userId=userId;
	}
	
	public UserId getUserId()
	{
		return userId;
	}
}
