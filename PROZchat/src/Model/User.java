package Model;


import java.util.HashMap;
import java.util.HashSet;

import Helpfull.Pair;

/*
 * Klasa u¿ytkownika chatu, zawiera unikalny numer u¿ytkownika,zbiór jego kontaktów, datê logowania i ostatniego pingu,
 * oraz historiê rozmowy danego u¿ytkownika 
 */
public class User {
 private int uniqueID;
 private HashSet<Pair> contacts;
 private HashMap<Integer, HashSet<Message>> chatHistory;
 
 public User(int uniqueID){
	 this.uniqueID=uniqueID;
	 contacts= new HashSet<Pair>();
	 chatHistory= new HashMap<Integer, HashSet<Message>>();
 }
 
 public int getUserId(){
	 return this.uniqueID;
 }
 
 public void addContact(int id, String name){
	 contacts.add(new Pair(id, name));
 }
 
 public HashSet<Message> getMessageHistory (int id){
	return chatHistory.get(id);		 
 }
 
 public HashSet<Pair> getContacts(){
	 return this.contacts;
 }
 
 public void addMessage(User user, String whenWritten, String msg){
	 Message message = new Message(user, whenWritten, msg);
	 chatHistory.get(user.uniqueID).add(message);	
 }
 public void removeHistory(int id){
	 chatHistory.get(id).clear();
 }
}











