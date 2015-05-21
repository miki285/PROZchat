package AplicationEvent;

import java.io.Serializable;

public class ButtonLoginClickedEvent extends ApplicationEvent implements Serializable {
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		private int id;
		
		private String name;
		
		public ButtonLoginClickedEvent(int id, String name){
			this.id=id;
			this.name= name;
		}
		
		public int getId()
		{
			return this.id;
		}
		
		public String getName()
		{
			return this.name;
		}
}
