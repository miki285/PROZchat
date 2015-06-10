package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;
import java.util.HashSet;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;

/**
 * Klasa zawieraj¹ca Set wszysktich u¿ytkowników i znajomych danego kliena
 * 
 * @author Miko³aj
 *
 */
public class SendUserContactsDummy extends SendDummy implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * HashSet zawieraj¹cy u¿ytkowników
	 */
	private HashSet<UsersDataForClient> usersFriends;
	private HashSet<UsersDataForClient> allUsers;

	/**
	 * Konstruktor tworz¹cy makietê
	 */
	public SendUserContactsDummy(HashSet<UsersDataForClient> usersFriends,
			HashSet<UsersDataForClient> allUsers)
	{
		this.usersFriends = usersFriends;
		this.allUsers = allUsers;
	}

	/**
	 * metoda zwracaj¹ca HashSet kontaktów u¿ytkownika
	 * 
	 * @return
	 */

	public HashSet<UsersDataForClient> getUsersContacts()
	{
		return this.usersFriends;
	}

	/**
	 * Metoda zwracaj¹ca HashSet wszyskich u¿ytkowników serwera
	 * 
	 * @return
	 */
	public HashSet<UsersDataForClient> getAllUsers()
	{
		return this.allUsers;
	}

}
