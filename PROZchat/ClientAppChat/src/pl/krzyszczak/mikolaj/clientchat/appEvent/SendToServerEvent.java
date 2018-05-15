/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.appEvent;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * 
 * @author Miko³aj
 *
 */
public class SendToServerEvent extends ApplicationEvent
{
	private ApplicationEvent applicationEvent;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SendToServerEvent(ApplicationEvent applicationEvent)
	{
		this.applicationEvent=applicationEvent;
	}
	
	public void setByUserId(UserId byUserId)
	{
		applicationEvent.setByUserId(byUserId);
	}
	
	public ApplicationEvent getApplicationEvent()
	{
		return applicationEvent;
	}
	

}
