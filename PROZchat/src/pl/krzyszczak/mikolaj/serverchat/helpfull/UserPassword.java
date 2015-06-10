package pl.krzyszczak.mikolaj.serverchat.helpfull;

import java.io.Serializable;

public class UserPassword implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Wartoœæ skrótu has³a u¿ytkownika
	 */
	private int password;

	/**
	 * konstruktor klasy userPassword
	 */
	public UserPassword(int password)
	{
		this.password = password;
	}

	/**
	 * Nadpisana metoda equals
	 * 
	 * @param Object
	 *            other
	 */
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (other instanceof Integer)
		{
			Integer otherId = (int) other;
			return otherId.equals(this.password);
		} else if (other instanceof UserPassword)
		{
			Integer password = ((UserPassword) other).getUserPassword();
			return password.equals(this.password);
		}

		return false;
	}

	public int getUserPassword()
	{
		return this.password;
	}
}
