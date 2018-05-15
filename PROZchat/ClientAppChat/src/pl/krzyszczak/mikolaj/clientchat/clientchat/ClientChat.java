/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.clientchat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import pl.krzyszczak.mikolaj.clientchat.connection.MainClientClass;
import pl.krzyszczak.mikolaj.clientchat.controller.Controller;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
/**
 * Klasa z funkcj¹ main dla aplikacji obs³uguj¹cej klienta
 * @author Miko³aj
 *
 */
public class ClientChat
{
	public static void main(String[] args)
	{
		//kolejka na eventy
		BlockingQueue<ApplicationEvent> eventQueue= new SynchronousQueue<ApplicationEvent>();
		MainClientClass client= new MainClientClass("localhost", 1200, eventQueue);
		Controller controller= new Controller(eventQueue, client);
		controller.work();
		

		
	
	}

}
