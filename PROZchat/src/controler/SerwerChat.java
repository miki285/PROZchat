package controler;

import helpfull.UserId;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;




import connection.MainServerClass;
import appEvent.ApplicationEvent;
import model.Model;

public class SerwerChat {
	public static void main(String[] args) {
		BlockingQueue<ApplicationEvent> eventQueue = new SynchronousQueue<ApplicationEvent>();
		System.out.println("Model Start");
		Model model= new Model();
		System.out.println("Serwer start");
		MainServerClass server= new MainServerClass (1200, eventQueue);
		
		UserId user;
		user= new UserId(15);
		if(user.equals(14)) System.out.println("Cos");
		if(user.equals(15)) System.out.println("Tak");
	}
	
	
}
