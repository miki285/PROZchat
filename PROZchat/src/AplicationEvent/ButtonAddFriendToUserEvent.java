package AplicationEvent;

import java.io.Serializable;

public class ButtonAddFriendToUserEvent extends ApplicationEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int byUser;
	private int whichUser;
	
	public ButtonAddFriendToUserEvent(int byUser, int whichUser){
		this.byUser=byUser;
		this.whichUser=whichUser;
	}
	
	public int getByUser(){
		return this.byUser;
	}
	
	public int getWhichUser(){
		return this.whichUser;
	}
}
