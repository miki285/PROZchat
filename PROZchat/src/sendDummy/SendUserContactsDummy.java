package sendDummy;

import java.io.Serializable;
import java.util.HashSet;

import model.User;

public class SendUserContactsDummy extends SendDummy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*HashSet zawieraj�cy u�ytkownik�w */
	private HashSet<User> usersFriends;
	private HashSet<User> allUsers;
	
	/*Konstruktor tworz�cy makiet�*/
	public SendUserContactsDummy(HashSet<User> usersFriends,HashSet<User> allUsers){
		this.usersFriends=usersFriends;
		this.allUsers=allUsers;
	}
	
	
	/*metoda zwracaj�ca HashSet kontakt�w u�ytkownika*/
	
	public HashSet<User> getUsersContacts(){
		return this.usersFriends;
	}
	
	public HashSet<User> getAllUsers(){
		return this.allUsers;
	}
	

}
