package Model;


/*
 * Klasa wiadomo�ci zawiera informacj�, kto napisa� wiadomo��, dat� oraz tre�� wiadomo�ci
 * @autor Krzyszczak Miko�aj
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
