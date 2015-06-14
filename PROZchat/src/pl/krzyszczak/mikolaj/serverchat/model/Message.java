package pl.krzyszczak.mikolaj.serverchat.model;

import java.io.Serializable;
import java.util.Date;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * Klasa wiadomo�ci zawiera informacj�, kto napisa� wiadomo��, dat� oraz tre��
 * wiadomo�ci
 * 
 * @autor Krzyszczak Miko�aj
 */
public class Message implements Comparable<Message>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3743243219628555010L;
	private UserId byUserId;
	private UserId toUserId;
	private Date whenWritten;
	private String message;

	/**
	 * konstruktor klasy Message
	 * 
	 * @param User
	 *            byUser, User toUser,Date whenWritten, String message
	 */
	public Message(UserId byUserId, UserId toUserId, Date whenWritten, String message)
	{
		this.byUserId = byUserId;
		this.toUserId = toUserId;
		this.whenWritten = whenWritten;
		this.message = message;
	}

	/**
	 * metoda zwracaj�ca tre�� danej wiadomo�ci
	 */
	public String getMessage()
	{
		return this.message;
	}

	/**
	 * Metoda zwracaj�ca dat� danej wiadomo�ci
	 */
	public Date getDate()
	{
		return this.whenWritten;
	}

	/**
	 * Metoda zwracaj�ca przez kogo zosta�a napisana wiadomo��
	 */
	public UserId getByUser()
	{
		return this.byUserId;
	}

	/**
	 * Metoda zwracaj�da do kogo zosta�a napisana wiadomo��
	 */
	public UserId getToUserId()
	{
		return this.toUserId;
	}

	/**
	 * Metoda za pomoc� kt�rej por�wnywane s� warto�ci DATA
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Message msg)
	{
		return getDate().compareTo(msg.getDate());
	}
}
