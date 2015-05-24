package sendDummy;

import helpfull.UsersDataForClient;

import java.io.Serializable;
import java.util.HashSet;


public class SendUserContactsDummy extends SendDummy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*HashSet zawieraj�cy u�ytkownik�w */
	private HashSet<UsersDataForClient> usersFriends;
	private HashSet<UsersDataForClient> allUsers;
	
	/*Konstruktor tworz�cy makiet�*/
	public SendUserContactsDummy(HashSet<UsersDataForClient> usersFriends,HashSet<UsersDataForClient> allUsers){
		this.usersFriends=usersFriends;
		this.allUsers=allUsers;
	}
	
	
	/*metoda zwracaj�ca HashSet kontakt�w u�ytkownika*/
	
	public HashSet<UsersDataForClient> getUsersContacts(){
		return this.usersFriends;
	}
	
	public HashSet<UsersDataForClient> getAllUsers(){
		return this.allUsers;
	}
	

}
