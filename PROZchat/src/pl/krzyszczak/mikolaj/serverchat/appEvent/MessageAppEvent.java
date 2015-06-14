package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

public class MessageAppEvent extends ApplicationEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserId byUserId;
	private final UserId toUserId;
	private final String msg;
	
	public MessageAppEvent(final UserId byUserId, final UserId toUserId, final String msg){
		this.byUserId=byUserId;
		this.toUserId=toUserId;
		this.msg=msg;
	}
	
	public UserId getByUser()
	{
		return this.byUserId;
	}
	
	public UserId getToUser()
	{
		return this.toUserId;
	}
	
	public String getMsg()
	{
		return this.msg;
	}
}
