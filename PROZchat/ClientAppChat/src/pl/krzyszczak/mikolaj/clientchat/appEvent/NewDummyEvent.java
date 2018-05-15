/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.appEvent;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendDummy;

/**
 * Event powstajacy u klienta kiety ten odbierze dowoln¹ makietê.
 * @author Miko³aj
 *
 */
public class NewDummyEvent extends ApplicationEvent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2529398507179679981L;
	/**Makieta otrzymana od serwera*/
	SendDummy dummy;
	
	public NewDummyEvent(SendDummy dummy)
	{
		this.dummy=dummy;
	}
	
	public SendDummy getDummy()
	{
		return dummy;
	}
	
}
