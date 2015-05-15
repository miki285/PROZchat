package Model;


/*
 * Klasa wiadomoœci zawiera informacjê, kto napisa³ wiadomoœæ, datê oraz treœæ wiadomoœci
 * @autor Krzyszczak Miko³aj
 */
public class Message {
	private User byUser;
	private String whenWritten;
	private String message;
	
	public Message (User byUser, String whenWritten, String message ){
		this.byUser=byUser;
		this.whenWritten=whenWritten;
		this.message=message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public String getDate(){
		return this.whenWritten;
	}
	
	public User getUser(){
		return this.byUser;
	}
}
