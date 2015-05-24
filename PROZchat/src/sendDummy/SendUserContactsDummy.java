package sendDummy;

import helpfull.UsersDataForClient;

import java.io.Serializable;
import java.util.HashSet;


public class SendUserContactsDummy extends SendDummy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*HashSet zawieraj¹cy u¿ytkowników */
	private HashSet<UsersDataForClient> usersFriends;
	private HashSet<UsersDataForClient> allUsers;
	
	/*Konstruktor tworz¹cy makietê*/
	public SendUserContactsDummy(HashSet<UsersDataForClient> usersFriends,HashSet<UsersDataForClient> allUsers){
		this.usersFriends=usersFriends;
		this.allUsers=allUsers;
	}
	
	
	/*metoda zwracaj¹ca HashSet kontaktów u¿ytkownika*/
	
	public HashSet<UsersDataForClient> getUsersContacts(){
		return this.usersFriends;
	}
	
	public HashSet<UsersDataForClient> getAllUsers(){
		return this.allUsers;
	}
	

}
