package Model;

import java.util.Date;


/*
 * Klasa wiadomo�ci zawiera informacj�, kto napisa� wiadomo��, dat� oraz tre�� wiadomo�ci
 * @autor Krzyszczak Miko�aj
 */
public class Message implements Comparable<Message> {
	private User byUser;
	private User toUser;
	private Date whenWritten;
	private String message;
	/*
	 * konstruktor klasy Message
	 * @param User byUser, User toUser,Date whenWritten, String message 
	 */
	public Message (User byUser, User toUser,Date whenWritten, String message ){
		this.byUser=byUser;
		this.toUser=toUser;
		this.whenWritten=whenWritten;
		this.message=message;
	}
	/*
	 * metoda zwracaj�ca tre�� danej wiadomo�ci
	 */
	public String getMessage(){
		return this.message;
	}
	/*
	 * Metoda zwracaj�ca dat� danej wiadomo�ci
	 */
	public Date getDate(){
		return this.whenWritten;
	}
	/*
	 * Metoda zwracaj�ca przez kogo zosta�a napisana wiadomo��
	 */
	public User getByUser(){
		return this.byUser;
	}
	/*
	 * Metoda zwracaj�da do kogo zosta�a napisana wiadomo��
	 */
	public User getToUser(){
		return this.toUser;
	}
    /*
     * Metoda za pomoc� kt�rej por�wnywane s� warto�ci DATA
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
	public int compareTo(Message msg) {
		return getDate().compareTo(msg.getDate());
	}
}

