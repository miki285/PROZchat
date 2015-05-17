package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import AplicationEvent.ApplicationEvent;

/*
 * Klasa s�u��ca do nas�uchiwania przychodz�cych po��cze� od klient�w
 * @autor Krzyszczak Miko�aj
 */
public class ConnectionListener extends Thread {
	//Wy�wietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	/*Server socket*/
	private ServerSocket serverSocket;
	/*Boolean czy serwer pracuje*/
	private boolean keepGoing;
	// referencja na kolejk� event�w
	private BlockingQueue<ApplicationEvent> eventQueue;
	// Mapa klient�w z socketami na podstawie ich id
	private HashMap<Integer, ClientThread> listenedClients;
	private HashMap<Integer, Socket> connectedClients;
	//unikalneID
	private int uniqueID;
	
	
	public ConnectionListener(ServerSocket serverSocket, BlockingQueue<ApplicationEvent> eventQueue,HashMap<Integer, Socket> connectedClients, HashMap<Integer, ClientThread> listenedClients ){
		this.serverSocket=serverSocket;
		this.keepGoing=true;
		this.eventQueue=eventQueue;
		this.uniqueID=1;
		this.listenedClients= listenedClients;
		this.connectedClients=connectedClients;
		simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		
	}
	
	public void run(){
		while(keepGoing){
			try{
				Socket clientSocket= serverSocket.accept();
				uniqueID++;
				ClientThread clientThread=new ClientThread( clientSocket, connectedClients, eventQueue, uniqueID);
				listenedClients.put(uniqueID, clientThread);
				clientThread.start();	
			}
			catch(IOException e){
				System.err.println(e);
			}
			display("Polaczono nowego klienta");
		}
	}
	
	//metoda do wy�wietlania wiadomosci z serwera razem z dat�
		private void display(String msg){
		String time=simpleDateFormat.format(new Date()) + " " + msg;
		System.out.println(time);
		}
		

}
