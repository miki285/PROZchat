package sendDummy;

import java.io.Serializable;
import java.util.HashSet;

import model.User;

public class SendUserContactsDummy extends SendDummy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*HashSet zawieraj¹cy u¿ytkowników */
	private HashSet<User> usersFriends;
	private HashSet<User> allUsers;
	
	/*Konstruktor tworz¹cy makietê*/
	public SendUserContactsDummy(HashSet<User> usersFriends,HashSet<User> allUsers){
		this.usersFriends=usersFriends;
		this.allUsers=allUsers;
	}
	
	
	/*metoda zwracaj¹ca HashSet kontaktów u¿ytkownika*/
	
	public HashSet<User> getUsersContacts(){
		return this.usersFriends;
	}
	
	public HashSet<User> getAllUsers(){
		return this.allUsers;
	}
	

}
