package pl.krzyszczak.mikolaj.serverchat.model;

import java.util.HashMap;
import java.util.HashSet;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;

/**
 * Klasa u¿ytkownika chatu, zawiera unikalny numer u¿ytkownika,zbiór jego
 * kontaktów, datê logowania i ostatniego pingu, oraz historiê rozmowy danego
 * u¿ytkownika
 */
public class User
{
	private UserId uniqueID;
	/** has³o u¿ytkownika */
	private UserPassword password;
	private String name;
	private HashSet<User> contacts;
	private HashMap<User, HashSet<Message>> chatHistory;

	/**
	 * Konstruktor klasy User
	 * 
	 * @param int uniqueID, String name
	 */
	public User(UserId uniqueID, String name, UserPassword password)
	{
		this.uniqueID = uniqueID;
		this.name = name;
		this.password = password;
		contacts = new HashSet<User>();
		chatHistory = new HashMap<User, HashSet<Message>>();
	}

	/**
	 * Metoda sprawdzaj¹ca poprawnoœæ has³a otrzymanego od u¿ytkownika
	 */

	public boolean checkPassword(UserPassword toCheck)
	{
		if (this.password.equals(toCheck))
			return true;
		return false;
	}

	/**
	 * Metoda zwracaj¹ca id danego klienta
	 */
	public UserId getUserId()
	{
		return uniqueID;
	}

	/**
	 * Metoda zwracaj¹ca nazwê danego klienta
	 */
	public String getUserName()
	{
		return name;
	}

	/**
	 * Metoda dodaj¹ca u¿ytkwonika do znajomych klienta
	 * 
	 * @param User user
	 */
	public void addContact(User user)
	{
		contacts.add(user);
		//chatHistory.put(user, new HashSet<Message>());
	}

	/**
	 * Metoda zwracaj¹ca HashSet wiadomoœci z danym u¿ytownikiem
	 * 
	 * @param User user
	 */
	public HashSet<Message> getMessageHistory(User user)
	{
		return chatHistory.get(user);
	}

	/**
	 * Metoda zwracaj¹ca listê znajomych danego u¿ytkownika
	 */
	public HashSet<User> getContacts()
	{
		return contacts;
	}

	/**
	 * Metoda dodaj¹ca wiadomoœæ do historii danego u¿ytkownika
	 * 
	 * @param User user,Message message
	 */
	public void addMessage(User user, Message message)
	{
		if(!chatHistory.containsKey(user))
		{
			chatHistory.put(user, new HashSet<Message>());
		}
		chatHistory.get(user).add(message);
	}


}
