package AplicationEvent;

import java.io.Serializable;

public class ButtonSendMessageEvent extends ApplicationEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int byUser;
	private int toUser;
	private String msg;
	
	public ButtonSendMessageEvent(int byUser, int toUser, String msg){
		this.byUser=byUser;
		this.toUser=toUser;
		this.msg=msg;
	}
	
	public int getByUser()
	{
		return this.byUser;
	}
	
	public int getToUser()
	{
		return this.toUser;
	}
	
	public String getMsg()
	{
		return this.msg;
	}
}
