package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;
import java.util.HashSet;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
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
	private UserId byUserId;

	/**
	 * Konstruktor tworz¹cy makietê
	 */
	public SendUserContactsDummy(HashSet<UsersDataForClient> usersFriends,
			HashSet<UsersDataForClient> allUsers,UserId byUserId)
	{
		this.usersFriends = usersFriends;
		this.allUsers = allUsers;
		this.byUserId=byUserId;
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

	/**
	 * Metoda zwracaj¹ca UserId u¿ytkownika przez którego ta makieta powsta³a
	 * 
	 * @return
	 */
	public UserId getByUserId()
	{
		return this.byUserId;
	}	
}
