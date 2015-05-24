package model;


import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import exceptions.MessagesNotFoundException;
import exceptions.UserNotFoundException;
import modelMessages.DataMessage;
import modelMessages.InfoMessage;
import modelMessages.ModelMessages;
import Helpfull.ByteArray;
import Helpfull.UserId;



public class Model {
	//Do przechowywania user�w
	private HashSet<User> usersSet;
	//format daty
	private SimpleDateFormat sdf;
	private PrivateKey privKey;
	private PublicKey pubKey;
	private int uniqueId;
	
	

	/*
	 * Konstruktor modelu
	 */
	public Model(){
		usersSet= new HashSet< User>();
		sdf= new SimpleDateFormat ("HH:mm:ss");
		uniqueId=1;
		try {
			setPairKeys();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
/*Metoda generuj�ca par� kluczy w kodowaniu RSA*/
	
	private void setPairKeys() throws NoSuchAlgorithmException{
		KeyPairGenerator kGen = null;
		kGen = KeyPairGenerator.getInstance("RSA");
		kGen.initialize(1024);
		KeyPair keyPair=kGen.generateKeyPair();
		this.privKey=keyPair.getPrivate();
		this.pubKey=keyPair.getPublic();
	}
	
	/*Metoda zwracaj�ca mo�liw� warto�� ID klienta */
	
	public ModelMessages getFreeId(){
		return new DataMessage(uniqueId++);
	}
	
	
	/*Metoda zwracaj�ca klucz publiczny*/
	public ModelMessages getPublicKey(){
		return new DataMessage(pubKey);
	}
	
	
	/*
	 * Metoda sprawdzaj�ca poprawno�� has�a zalogowanego u�ytkownika
	 */
	
	public ModelMessages checkUserPassword(UserId userId, ByteArray password){
		User user;
		try{
			user=getUser(userId);
			user.checkPassword(privKey, password);
			
		}
		catch(UserNotFoundException e){
			return new InfoMessage("Nie ma u�ytkownika o takim numerze ID");
		}
		catch (NoSuchAlgorithmException e){
			System.err.println(e);
		}
		catch (BadPaddingException e) {
			System.err.println(e);
		}
		catch (IllegalBlockSizeException e) {
			System.err.println(e);
		}
		catch (InvalidKeyException e) {
			System.err.println(e);
		}
		catch (NoSuchPaddingException e) {
			System.err.println(e);
		}
		
		return new DataMessage(this.usersSet);
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
		
		return this.sendMessageHistory(sender, taker);
	}
	
	
	/*
	 * Metoda dodaj�ca u�ytkownik�w do HashMapy u�ytkownik�w na serwerze
	 * @param int id, String name
	 */
	public void addUser(UserId id, String name, ByteArray password){
		usersSet.add(new User(id, name, password));
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
		return sendMessageHistory(sender, taker);
	}
	
	/*
	 * Prywatna metoda tworz�ca rozmow� mi�dzy dwoma u�ytkownikami po ich ID
	 * 	 @param int idSender, int idTaker
	 */
	private String getMessageHistory (User sender, User taker)throws MessagesNotFoundException{
		HashSet<Message> conversation= new HashSet<Message>();
		String finishConversation= new String ("");
		
		conversation.addAll(sender.getMessageHistory(taker));
		if(!conversation.isEmpty()){
			ArrayList<Message> listOfMessages= new ArrayList<Message>();
			listOfMessages.addAll(conversation);
			Collections.sort(listOfMessages);
			
			for(Message msg : listOfMessages){
				finishConversation = finishConversation + msg.getMessage();
			}
		}		
		throw new MessagesNotFoundException();
	}
	
	
	/*
	 * Prywatna klasa do wysy�ania wiadomo�ci
	 */
	private ModelMessages sendMessageHistory(User sender, User taker){
		String msg;
		try
		{
			msg=this.getMessageHistory(sender, taker);
		}catch (MessagesNotFoundException e){
			return new InfoMessage("Brak wiadomo�ci do wy�wietlenia");
		}
		return new DataMessage(msg);
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