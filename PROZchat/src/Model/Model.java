package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;


import Helpfull.Pair;

public class Model {
	HashMap<Integer, User> usersMap;
	HashSet<User> activeUser;
	//Wyświetlanie czasu
	private SimpleDateFormat simpleDateFormat;
	
	public Model(){
		simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		usersMap = new HashMap<Integer, User>();
		activeUser= new HashSet<User>();
	}
	
	public void addActiveUser(User user){
		activeUser.add(user);
	}
	
	public void addUserMessage(int idSender, int idTaker, String msg){
		String time = simpleDateFormat.format(new Date());
		User sender = usersMap.get(idSender);
		User taker= usersMap.get(idTaker);
		sender.addMessage(sender, time, msg);
		taker.addMessage(sender, time, msg);
	}
	
	public HashSet<User> getActiveUser(){
		return activeUser;
	}
	
	public HashSet<Pair> getUserContacts(int id){
		return usersMap.get(id).getContacts();
	}
	
	public HashSet<Message> createUserConversation (int idSender, int idTaker){
		return usersMap.get(idSender).getMessageHistory(idTaker);
	}
}
