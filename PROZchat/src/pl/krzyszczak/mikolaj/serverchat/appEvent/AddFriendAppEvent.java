package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

public class AddFriendAppEvent extends ApplicationEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserId byUserId;
	private UserId whichUserId;
	
	public AddFriendAppEvent (UserId whichUserId){
		this.byUserId=null;
		this.whichUserId=whichUserId;
	}
	
	/**Metoda ustawiaj¹ca byUserId*/
	
	public void setByUserId(UserId byUserId)
	{
		this.byUserId=byUserId;
	}
	
	public UserId getByUser(){
		return this.byUserId;
	}
	
	public UserId getWhichUser(){
		return this.whichUserId;
	}
}
