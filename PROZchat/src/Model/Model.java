package Model;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import Exceptions.UserNotFoundException;
import Message.DataMessage;
import Message.InfoMessage;
import Message.ModelMessages;



public class Model {
	//Do przechowywania user�w
	HashSet<User> usersSet;
	SimpleDateFormat sdf;
	

	/*
	 * Konstruktor modelu
	 */
	public Model(){
		usersSet= new HashSet< User>();
		sdf= new SimpleDateFormat ("HH:mm:ss");
	}
	
	
	/*
	 * Metoda dodaj�ca wiadomo�� do ka�dego z u�ytkownik�w danej konwersacji
	 * @param int idSender, int idTaker, String msg
	 */
	public ModelMessages addUserMessage(UserId idSender, UserId idTaker, String msg){
		User sender;
		User taker;
		
		try {
			sender = getUser(idSender);
			taker= getUser(idTaker);
			Date date= Calendar.getInstance().getTime();
			Message message= new Message(sender, taker,date ,sdf.format(date)+ " " + sender.getUserName()+"\n"+msg+"\n");
			sender.addMessage(taker, message);
			taker.addMessage(sender, message);
		} catch (UserNotFoundException e) {
			return new InfoMessage("Nie ma uzytkownikow o takich numerach ID");
		}
		
		return new DataMessage(this.getMessageHistory(sender, taker));
	}
	
	
	/*
	 * Metoda dodaj�ca u�ytkownik�w do HashMapy u�ytkownik�w na serwerze
	 * @param int id, String name
	 */
	public void addUser(UserId id, String name){
		usersSet.add(new User(id, name));
	}
	
	
	/*
	 * Metoda zwracaj�ca kontakty danego u�ytkownika
	 * @param int id
	 */
	public ModelMessages getUsersContacts (UserId id){
		User user;
		try{
			user=getUser(id);
		}
		catch( UserNotFoundException e){
			return new InfoMessage("Nie ma uzytkownikow o takich numerach ID");
		}
		return new DataMessage(this.getUserContacts(user));
	}
	
	
	
	/*
	 * Metoda dodaj�ca u�ytkownika do znajomych 
	 * @param int idSender, int idTaker
	 */
	public ModelMessages addUserContact(UserId idSender, UserId idTaker){
		User sender; 
		User taker;
		try{
			sender=getUser(idSender);
			taker=getUser(idTaker);
			sender.addContact(taker);
		}
		catch( UserNotFoundException e){
			return new InfoMessage("Nie ma uzytkownika o takim numerze ID");
		}
		return this.getUsersContacts(idSender);
	}
	
	
	/*
	 * Metoda zwracaj�ca ca�� rozmow� mi�dzy dwoma u�ytkownikami po ich id
	 * @param int idSender, int idTaker
	 * p�ki co jest bez kasowania
	 */
	public ModelMessages getMessageHisory( UserId idSender, UserId idTaker){
		User sender;
		User taker;
	
		try {
			sender=getUser(idSender);
			taker=getUser(idTaker);
		} catch (UserNotFoundException e) {
			return new InfoMessage("Nie ma uzytkownikow o takich numerach id");
		}
		return new DataMessage(this.getMessageHistory(sender, taker));
	}
	
	/*
	 * Prywatna metoda tworz�ca rozmow� mi�dzy dwoma u�ytkownikami po ich ID
	 * 	 @param int idSender, int idTaker
	 */
	private String getMessageHistory (User sender, User taker){
		HashSet<Message> conversation= new HashSet<Message>();
		String finishConversation= new String ("");
		
		conversation.addAll(sender.getMessageHistory(taker));
		conversation.addAll(taker.getMessageHistory(sender));
		if(!conversation.isEmpty()){
			ArrayList<Message> listOfMessages= new ArrayList<Message>();
			listOfMessages.addAll(conversation);
			Collections.sort(listOfMessages);
			
			for(Message msg : listOfMessages){
				finishConversation = finishConversation + msg.getMessage();
			}
		}		
		return finishConversation;
	}
	
	
	/*
	 * Prywatna klasa pomocnicza zwracaj�ca usera z UserSet'a
	 */
	private User getUser(UserId id)throws UserNotFoundException{
		for (User user: usersSet)
		{
			if(id.equals(user.getUserId()))
			{
				return user;
			}
		}
		throw new UserNotFoundException();
	}
	
	
	/*
	 * Metoda prywatna pomagaj�ca przy zwracaniu informacji o kontaktach u�ytkownika
	 */
	private HashSet<User> getUserContacts(User user){
		return user.getContacts();		
}
	/*
	public void deleteHistory(int idSender, int idTaker){
		usersMap.get(idSender).removeHistory(idTaker);
	}
	*/
}
