package pl.krzyszczak.mikolaj.serverchat.model;

import java.util.Date;


/*
 * Klasa wiadomoœci zawiera informacjê, kto napisa³ wiadomoœæ, datê oraz treœæ wiadomoœci
 * @autor Krzyszczak Miko³aj
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
	 * metoda zwracaj¹ca treœæ danej wiadomoœci
	 */
	public String getMessage(){
		return this.message;
	}
	/*
	 * Metoda zwracaj¹ca datê danej wiadomoœci
	 */
	public Date getDate(){
		return this.whenWritten;
	}
	/*
	 * Metoda zwracaj¹ca przez kogo zosta³a napisana wiadomoœæ
	 */
	public User getByUser(){
		return this.byUser;
	}
	/*
	 * Metoda zwracaj¹da do kogo zosta³a napisana wiadomoœæ
	 */
	public User getToUser(){
		return this.toUser;
	}
    /*
     * Metoda za pomoc¹ której porównywane s¹ wartoœci DATA
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
	public int compareTo(Message msg) {
		return getDate().compareTo(msg.getDate());
	}
}

