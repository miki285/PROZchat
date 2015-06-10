package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;
import java.util.HashSet;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;

/**
 * Klasa zawieraj�ca Set wszysktich u�ytkownik�w i znajomych danego kliena
 * 
 * @author Miko�aj
 *
 */
public class SendUserContactsDummy extends SendDummy implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * HashSet zawieraj�cy u�ytkownik�w
	 */
	private HashSet<UsersDataForClient> usersFriends;
	private HashSet<UsersDataForClient> allUsers;

	/**
	 * Konstruktor tworz�cy makiet�
	 */
	public SendUserContactsDummy(HashSet<UsersDataForClient> usersFriends,
			HashSet<UsersDataForClient> allUsers)
	{
		this.usersFriends = usersFriends;
		this.allUsers = allUsers;
	}

	/**
	 * metoda zwracaj�ca HashSet kontakt�w u�ytkownika
	 * 
	 * @return
	 */

	public HashSet<UsersDataForClient> getUsersContacts()
	{
		return this.usersFriends;
	}

	/**
	 * Metoda zwracaj�ca HashSet wszyskich u�ytkownik�w serwera
	 * 
	 * @return
	 */
	public HashSet<UsersDataForClient> getAllUsers()
	{
		return this.allUsers;
	}

}
