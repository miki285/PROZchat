package sendDummy;

import java.io.Serializable;
import java.util.HashSet;

import model.User;

public class SendUserContactsDummy extends SendDummy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*HashSet zawierający użytkowników */
	private HashSet<User> usersFriends;
	private HashSet<User> allUsers;
	
	/*Konstruktor tworzący makietę*/
	public SendUserContactsDummy(HashSet<User> usersFriends,HashSet<User> allUsers){
		this.usersFriends=usersFriends;
		this.allUsers=allUsers;
	}
	
	
	/*metoda zwracająca HashSet kontaktów użytkownika*/
	
	public HashSet<User> getUsersContacts(){
		return this.usersFriends;
	}
	
	public HashSet<User> getAllUsers(){
		return this.allUsers;
	}
	

}
