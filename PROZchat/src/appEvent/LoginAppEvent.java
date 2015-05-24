package appEvent;

import java.io.Serializable;

import Helpfull.ByteArray;
import Helpfull.UserId;

public class LoginAppEvent extends ApplicationEvent implements Serializable {
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		private UserId id;
		
		private String name;
		
		private ByteArray password;
		
		public LoginAppEvent(UserId id, String name, ByteArray password){
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
