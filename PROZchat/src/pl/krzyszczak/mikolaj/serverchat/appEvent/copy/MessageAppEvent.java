package pl.krzyszczak.mikolaj.serverchat.appEvent.copy;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

public class MessageAppEvent extends ApplicationEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserId byUser;
	private final UserId toUser;
	private final String msg;
	
	public MessageAppEvent(final UserId byUser, final UserId toUser, final String msg){
		this.byUser=byUser;
		this.toUser=toUser;
		this.msg=msg;
	}
	
	public UserId getByUser()
	{
		return this.byUser;
	}
	
	public UserId getToUser()
	{
		return this.toUser;
	}
	
	public String getMsg()
	{
		return this.msg;
	}
}
