/**
 * 
 */
package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.ObjectOutputStream;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;

/**
 * @author Miko³aj
 *
 */
public class LoginAppServerEvent extends ApplicationEvent
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserId id;
	private UserPassword password;
	private ObjectOutputStream objectOutputStream;

	public LoginAppServerEvent(LoginAppClientEvent loginAppClientEvent, ObjectOutputStream objectOutputStream)
	{
		this.id = loginAppClientEvent.getUserId();
		this.password = loginAppClientEvent.getPassword();
		this.objectOutputStream = objectOutputStream;
	}

	public UserId getId()
	{
		return this.id;
	}

	public UserPassword getPassword()
	{
		return this.password;
	}

	public ObjectOutputStream getObjectOutputStream()
	{
		return objectOutputStream;
	}

}
