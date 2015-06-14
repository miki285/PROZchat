package pl.krzyszczak.mikolaj.serverchat.model;

import java.io.Serializable;
import java.util.Date;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * Klasa wiadomoœci zawiera informacjê, kto napisa³ wiadomoœæ, datê oraz treœæ
 * wiadomoœci
 * 
 * @autor Krzyszczak Miko³aj
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
	 * metoda zwracaj¹ca treœæ danej wiadomoœci
	 */
	public String getMessage()
	{
		return this.message;
	}

	/**
	 * Metoda zwracaj¹ca datê danej wiadomoœci
	 */
	public Date getDate()
	{
		return this.whenWritten;
	}

	/**
	 * Metoda zwracaj¹ca przez kogo zosta³a napisana wiadomoœæ
	 */
	public UserId getByUser()
	{
		return this.byUserId;
	}

	/**
	 * Metoda zwracaj¹da do kogo zosta³a napisana wiadomoœæ
	 */
	public UserId getToUserId()
	{
		return this.toUserId;
	}

	/**
	 * Metoda za pomoc¹ której porównywane s¹ wartoœci DATA
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Message msg)
	{
		return getDate().compareTo(msg.getDate());
	}
}
