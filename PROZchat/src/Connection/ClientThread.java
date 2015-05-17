package Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import SendDummy.SendDummy;
import AplicationEvent.ApplicationEvent;
import AplicationEvent.ButtonLoginClickedEvent;

public class ClientThread extends Thread {
	
	// unikalne id klienta
	private int id;
	// Strumien wejsciowy 
	private ObjectInputStream inStream;
	//Strumien wyjsciowy
	private ObjectOutputStream outStream;
	//referencja do eventQueue
	private BlockingQueue<ApplicationEvent> eventQueue;
	//referencja do connectedClients
	private HashMap<Integer, Socket> connectedClients;
	SimpleDateFormat simpleDateFormat;
	
	
	ClientThread(Socket socket, HashMap<Integer, Socket> connectedClients, BlockingQueue<ApplicationEvent> eventQueue, int id){
		this.connectedClients=connectedClients;
		this.eventQueue=eventQueue;
		this.id=id;
		try{
			outStream= new ObjectOutputStream(socket.getOutputStream());
			inStream= new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException e){
			System.err.println("Blad przy tworzeniu strumienia serwera " + e);
			return;
		}
	}
	
	public void run(){
		ApplicationEvent applicationEvent;
		String time=simpleDateFormat.format(new Date()) + "Czekam na akcjê od uzytkownika";
		System.out.println(time);
		while(true){
			try{
				applicationEvent= (ApplicationEvent) inStream.readObject();
				
				if (applicationEvent instanceof ButtonLoginClickedEvent){
					/*@TODO*/
				}
				
				eventQueue.add(applicationEvent);
			}
			catch(IOException e){
				System.err.println(e);
				connectedClients.remove(id);
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
