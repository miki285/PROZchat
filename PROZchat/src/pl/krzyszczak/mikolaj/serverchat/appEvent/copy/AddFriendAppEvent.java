package pl.krzyszczak.mikolaj.serverchat.appEvent.copy;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

public class AddFriendAppEvent extends ApplicationEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserId byUser;
	private UserId whichUser;
	
	public AddFriendAppEvent(UserId byUser, UserId whichUser){
		this.byUser=byUser;
		this.whichUser=whichUser;
	}
	
	public UserId getByUser(){
		return this.byUser;
	}
	
	public UserId getWhichUser(){
		return this.whichUser;
	}
}
