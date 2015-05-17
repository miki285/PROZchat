package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;


import Helpfull.Pair;

public class Model {
	HashMap<Integer, User> usersMap;
	//HashSet<User> activeUser;
	//Wyœwietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	
	public Model(){
		simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		//activeUser= new HashSet<User>();
		usersMap= new HashMap<Integer, User>();
	}
	

	
	/*public void addActiveUser(int id){
		activeUser.add(usersMap.get(id));
	}
	public HashSet<User> getActiveUser(){
		return activeUser;
	}
	public HashSet<User> getActiveUser(){
		return activeUser;
	}
	*/
	public void addUserMessage(int idSender, int idTaker, String msg){
		String time = simpleDateFormat.format(new Date());
		User sender = usersMap.get(idSender);
		User taker= usersMap.get(idTaker);
		sender.addMessage(sender, time, msg);
		taker.addMessage(sender, time, msg);
	}
	
	public void addUser(int id){
		usersMap.put(id, new User(id));
	}
	
	public HashSet<Pair> getUserContacts(int id){
		return usersMap.get(id).getContacts();
	}
	
	public void addUserContact(int idSender, int idToAdd, String name){
		usersMap.get(idSender).addContact(idToAdd, name);
	}
	
	public HashSet<Message> getMessageHistory (int idSender, int idTaker){
		return usersMap.get(idSender).getMessageHistory(idTaker);
	}
	
	public void deleteHistory(int idSender, int idTaker){
		usersMap.get(idSender).removeHistory(idTaker);
	}
	
}
