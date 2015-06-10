package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;

public class LoginAppClientEvent extends ApplicationEvent implements
		Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserId id;

	private UserPassword password;

	public LoginAppClientEvent(UserId id, UserPassword password)
	{
		this.id = id;
		this.password = password;
	}

	public UserId getId()
	{
		return this.id;
	}

	public UserPassword getPassword()
	{
		return this.password;
	}
}
