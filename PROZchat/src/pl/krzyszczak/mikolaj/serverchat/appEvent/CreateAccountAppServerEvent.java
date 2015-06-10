/**
 * 
 */
package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.ObjectOutputStream;
import java.io.Serializable;


import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;

/**
 * @author Miko³aj
 *
 */
public class CreateAccountAppServerEvent extends ApplicationEvent implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private String name;
	private UserPassword password;
	private ObjectOutputStream objectOutputStream;

	/**
	 * Konstruktor klasy eventu utwórz konto
	 * 
	 * @param name
	 * @param password
	 */
	public CreateAccountAppServerEvent(String name, UserPassword password, ObjectOutputStream objectOutputStream)
	{

		this.name = name;
		this.password = password;
		this.objectOutputStream=objectOutputStream;
	}

	public String getName()
	{
		return this.name;
	}
	
	public ObjectOutputStream getObjectOutputStream()
	{
		return this.objectOutputStream;
	}

	public UserPassword getPassword()
	{
		return this.password;
	}

}
