package pl.krzyszczak.mikolaj.serverchat.helpfull;

import java.io.Serializable;

/**
 * Klasa UserId s³u¿y do przechowywania numeru ID danego u¿ytkownika
 * 
 * @author Miko³aj
 *
 */
public class UserId implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id u¿ytkownika
	 */
	private int userID;

	/**
	 * Konstruktor userid
	 */
	public UserId(final int userID)
	{
		this.userID = userID;
	}

	/**
	 * Nadpisana metoda equals
	 * 
	 * @param Object
	 *            other
	 */
	@Override
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (other instanceof Integer)
		{
			Integer otherId = (int) other;
			return otherId.equals(this.userID);
		} else if (other instanceof UserId)
		{
			Integer id = ((UserId) other).getId();
			return id.equals(this.userID);
		}
		return false;

	}

	/**
	 * zwraca id u¿ytkownika
	 */
	public int getId()
	{
		return this.userID;
	}
}
