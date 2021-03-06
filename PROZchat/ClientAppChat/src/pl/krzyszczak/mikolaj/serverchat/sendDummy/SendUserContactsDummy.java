package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;
import java.util.HashSet;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;

/**
 * Klasa zawierająca Set wszysktich użytkowników i znajomych danego kliena
 * 
 * @author Mikołaj
 *
 */
public class SendUserContactsDummy extends SendDummy implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashSet<UsersDataForClient> usersFriends;
	private HashSet<UsersDataForClient> allUsers;
	private UserId byUserId;

	/**
	 * Konstruktor tworzący makietę
	 */
	public SendUserContactsDummy(HashSet<UsersDataForClient> usersFriends,
			HashSet<UsersDataForClient> allUsers,UserId byUserId)
	{
		this.usersFriends = usersFriends;
		this.allUsers = allUsers;
		this.byUserId=byUserId;
	}

	/**
	 * metoda zwracająca HashSet kontaktów użytkownika
	 * 
	 * @return
	 */

	public HashSet<UsersDataForClient> getUsersContacts()
	{
		return this.usersFriends;
	}

	/**
	 * Metoda zwracająca HashSet wszyskich użytkowników serwera
	 * 
	 * @return
	 */
	public HashSet<UsersDataForClient> getAllUsers()
	{
		return this.allUsers;
	}

	/**
	 * Metoda zwracająca UserId użytkownika przez którego ta makieta powstała
	 * 
	 * @return
	 */
	public UserId getByUserId()
	{
		return this.byUserId;
	}	
}
