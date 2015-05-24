package appEvent;

import helpfull.ByteArray;
import helpfull.UserId;

import java.io.Serializable;

public class CreateAccountAppEvent extends ApplicationEvent implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UserId id;
	private String name;
	private ByteArray password;
	
	public CreateAccountAppEvent(UserId id, String name, ByteArray password){
		this.id=id;
		this.name= name;
		this.password=password;
	}
	
	public UserId getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public ByteArray getPassword(){
		return this.password;
	}
	
	

}
