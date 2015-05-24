package sendDummy;

import helpfull.UsersDataForClient;

import java.io.Serializable;
import java.util.HashSet;


public class SendUserContactsDummy extends SendDummy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*HashSet zawierający użytkowników */
	private HashSet<UsersDataForClient> usersFriends;
	private HashSet<UsersDataForClient> allUsers;
	
	/*Konstruktor tworzący makietę*/
	public SendUserContactsDummy(HashSet<UsersDataForClient> usersFriends,HashSet<UsersDataForClient> allUsers){
		this.usersFriends=usersFriends;
		this.allUsers=allUsers;
	}
	
	
	/*metoda zwracająca HashSet kontaktów użytkownika*/
	
	public HashSet<UsersDataForClient> getUsersContacts(){
		return this.usersFriends;
	}
	
	public HashSet<UsersDataForClient> getAllUsers(){
		return this.allUsers;
	}
	

}
