package controler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import AplicationEvent.ApplicationEvent;
import Connection.Server;
import Model.Model;
import Model.UserId;

public class SerwerChat {
	public static void main(String[] args) {
		//BlockingQueue<ApplicationEvent> eventQueue = new SynchronousQueue<ApplicationEvent>();
		/*System.out.println("Model Start");
		Model model= new Model();
		System.out.println("Serwer start");
		Server server= new Server(eventQueue, 1200);
		*/
		UserId user;
		user= new UserId(15);
		if(user.equals(14)) System.out.println("Cos");
		if(user.equals(15)) System.out.println("Tak");
	}
	
	
}
