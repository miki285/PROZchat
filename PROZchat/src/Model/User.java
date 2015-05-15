package Model;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import Helpfull.Pair;

/*
 * Klasa u¿ytkownika chatu, zawiera unikalny numer u¿ytkownika,zbiór jego kontaktów, datê logowania i ostatniego pingu,
 * oraz historiê rozmowy danego u¿ytkownika 
 */
public class User {
 private int uniqueID;
 private LinkedList<Pair> contacts;
 private HashMap<Integer, HashSet<Message>> chatHistory;
 
 public User(int uniqueID){
	 this.uniqueID=uniqueID;
	 contacts= new LinkedList<Pair>();
	 chatHistory= new HashMap<Integer, HashSet<Message>>();
 }
 
 public int getUserId(){
	 return this.uniqueID;
 }
 
 public HashSet<Message> getMessageHistory (int id){
	return chatHistory.get(id);		 
 }
 
 public LinkedList<Pair> getUserContacts(){
	 return this.contacts;
 }
 
 public void addMessage(User user, String whenWritten, String msg){
	 Message message = new Message(user, whenWritten, msg);
	 chatHistory.get(user.uniqueID).add(message);	
 }
}











