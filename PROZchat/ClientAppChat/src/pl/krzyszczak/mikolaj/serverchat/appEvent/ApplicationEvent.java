package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * Abstrakcyjna klasa bazowa dla wszystkich zdarze�.
 * 
 * @author Krzyszczak Miko�aj
 *
 */
public abstract class ApplicationEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void setByUserId(UserId byUserId)
	{
	}
}
