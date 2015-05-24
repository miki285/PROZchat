package connection;

import helpfull.UserId;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import appEvent.*;
import sendDummy.SendDummy;

public class ClientThread extends Thread {
	
	/* Strumien wejsciowy */
	private ObjectInputStream inStream;
	/*Strumien wyjsciowy*/
	private ObjectOutputStream outStream;
	/*referencja do eventQueue*/
	private BlockingQueue<ApplicationEvent> eventQueue;
	/*Mapa przetrzymuj¹ca strumienie wyjœciowe u¿ytkowników, rozró¿nienie po UserId*/
	private HashMap<UserId, ObjectOutputStream> userOutputStreamsMap;

	
	
	ClientThread(Socket socket, HashMap<UserId, ObjectOutputStream> userOutputStreamsMap, BlockingQueue<ApplicationEvent> eventQueue){
		this.userOutputStreamsMap=userOutputStreamsMap;
		this.eventQueue=eventQueue;
		try{
			outStream= new ObjectOutputStream(socket.getOutputStream());
			inStream= new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.err.println("Blad przy tworzeniu strumienia " + e);
			return;
		}
	}
	/*
	 * Metoda czekaj¹ca na Eventy od u¿ytkownika
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		ApplicationEvent listenedEvent;
		System.out.println("Czekam na akcjê od u¿ytkownika");
		while(true){
			try{
				listenedEvent= (ApplicationEvent) inStream.readObject();
				
				if (listenedEvent instanceof LoginAppEvent){
					LoginAppEvent loginListenedEvent=(LoginAppEvent) listenedEvent;
					userOutputStreamsMap.put(loginListenedEvent.getId(), outStream);
				}
				if(listenedEvent instanceof CreateAccountAppEvent){
					CreateAccountAppEvent createAccountAppEvent=(CreateAccountAppEvent) listenedEvent;
					userOutputStreamsMap.put(createAccountAppEvent.getId(), outStream);
				}
				else eventQueue.add(listenedEvent);
			}
			catch(IOException e){
				System.err.println(e);
				
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
				System.exit(1);
			}
			
		}
	}
	
	/*
	 * Metoda wysy³¹j¹ca makietê do klientów
	 * 
	 */
	public void sendMessage(SendDummy sendDummy){
		try {
			outStream.writeObject(sendDummy);
		}
		catch (IOException e) {
			System.err.println(e);
			
		}
	}
}
