package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;

public class LoginAppClientEvent extends ApplicationEvent implements
		Serializable
{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserId userId;

	private UserPassword password;

	public LoginAppClientEvent(UserId userId, UserPassword password)
	{
		this.userId = userId;
		this.password = password;
	}

	public UserId getUserId()
	{
		return this.userId;
	}

	public UserPassword getPassword()
	{
		return this.password;
	}
}
