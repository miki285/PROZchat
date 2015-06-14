package pl.krzyszczak.mikolaj.serverchat.serverchat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.connection.MainServerClass;
import pl.krzyszczak.mikolaj.serverchat.controler.Controller;
import pl.krzyszczak.mikolaj.serverchat.model.Model;

public class SerwerChat {
	public static void main(String[] args) {
		BlockingQueue<ApplicationEvent> eventQueue = new LinkedBlockingQueue<ApplicationEvent>();
		System.out.println("Model Start");
		Model model= new Model();
		System.out.println("Serwer start");
		MainServerClass server= new MainServerClass (1200, eventQueue);
		Controller controller= new Controller(eventQueue, model, server);
		controller.work();
		
		System.out.println("Serwer wystartowal");
	}
	
	
}
