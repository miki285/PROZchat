package pl.krzyszczak.mikolaj.serverchat.helpfull;

import java.io.Serializable;

public class UsersDataForClient implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* unikalne id klienta */
	private UserId uniqueID;
	/* zwyk³a nazwa u¿ytkownika */
	private String userName;

	public UsersDataForClient(UserId uniqueId, String name)
	{
		this.uniqueID = uniqueId;
		this.userName = name;
	}

	public String getName()
	{
		return this.userName;
	}

	public UserId getUserId()
	{
		return this.uniqueID;
	}
}
