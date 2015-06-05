package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;
import java.util.HashSet;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;

/**
 * Klasa zawierająca Set wszysktich użytkowników i znajomych danego kliena
 * 
 * @author Mikołaj
 *
 */
public class SendUserContactsDummy extends SendDummy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * HashSet zawierający użytkowników
	 */
	private HashSet<UsersDataForClient> usersFriends;
	private HashSet<UsersDataForClient> allUsers;

	/**
	 * Konstruktor tworzący makietę
	 */
	public SendUserContactsDummy(HashSet<UsersDataForClient> usersFriends,
			HashSet<UsersDataForClient> allUsers) {
		this.usersFriends = usersFriends;
		this.allUsers = allUsers;
	}

	/**
	 * metoda zwracająca HashSet kontaktów użytkownika
	 * 
	 * @return
	 */

	public HashSet<UsersDataForClient> getUsersContacts() {
		return this.usersFriends;
	}

	/**
	 * Metoda zwracająca HashSet wszyskich użytkowników serwera
	 * 
	 * @return
	 */
	public HashSet<UsersDataForClient> getAllUsers() {
		return this.allUsers;
	}

}
