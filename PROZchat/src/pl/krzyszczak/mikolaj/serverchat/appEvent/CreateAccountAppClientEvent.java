package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;

/**
 * Klasa eventu tworz¹cego nowe konto
 * 
 * @author Miko³aj
 *
 */
public class CreateAccountAppClientEvent extends ApplicationEvent implements
		Serializable
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private String name;
	private UserPassword password;

	/**
	 * Konstruktor klasy eventu utwórz konto
	 * 
	 * @param name
	 * @param password
	 */
	public CreateAccountAppClientEvent(String name, UserPassword password)
	{

		this.name = name;
		this.password = password;
	}

	public String getName()
	{
		return this.name;
	}

	public UserPassword getPassword()
	{
		return this.password;
	}

}
