package Model;



import java.util.HashMap;
import java.util.HashSet;



/*
 * Klasa u�ytkownika chatu, zawiera unikalny numer u�ytkownika,zbi�r jego kontakt�w, dat� logowania i ostatniego pingu,
 * oraz histori� rozmowy danego u�ytkownika 
 */
public class User {
 private UserId uniqueID;
 private String name;
 private HashSet<User> contacts;
 private HashMap<User, HashSet<Message>> chatHistory;
 /*
  * Konstruktor klasy User
  * @param int uniqueID, String name
  */
 public User(UserId uniqueID, String name){
	 this.uniqueID=uniqueID;
	 this.name=name;
	 contacts= new HashSet<User>();
	 chatHistory= new HashMap<User, HashSet<Message>>();
 }
 /*
  * Metoda zwracaj�ca id danego klienta
  */
 public int getUserId(){
	 return this.uniqueID.getId();
 }
 /*
  * Metoda zwracaj�ca nazw� danego klienta
  */
 public String getUserName(){
	 return this.name;
 }
 /*
  * Metoda dodaj�ca u�ytkwonika do znajomych klienta
  * @param User user
  */
 public void addContact(User user){
	 contacts.add(user);
	 chatHistory.put(user, new HashSet<Message>());
 }
 /*
  * Metoda zwracaj�ca HashSet wiadomo�ci z danym u�ytownikiem
  * @param User user
  */
 public HashSet<Message> getMessageHistory (User user){
		 return chatHistory.get(user);
 }
 /*
  * Metoda zwracaj�ca list� znajomych danego u�ytkownika
  */
public HashSet<User> getContacts()
{
	return contacts;
}
 /*
  * Metoda dodaj�ca wiadomo�� do historii danego u�ytkownika
  * @param User user,Message message
  */
 public void addMessage(User user,Message message){
	 chatHistory.get(user).add(message);	
 }
 public void removeHistory(User user){
	 chatHistory.get(user).clear();
 }

}











