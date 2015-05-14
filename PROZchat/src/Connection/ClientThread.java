package Connection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import AplicationEvent.ApplicationEvent;

public class ClientThread extends Thread {
	
	//Socket klienta
	private Socket socket;
	//Unikalne id klienta
	private int id;
	//data zalogowania
	private String logIn;
	// Strumien wejsciowy 
	private ObjectInputStream inStream;
	//Strumien wyjsciowy
	private ObjectOutputStream outStream;
	//referencja do eventQueue
	private BlockingQueue<ApplicationEvent> eventQueue;
	//referencja do connectedClients
	private HashMap<Integer, Socket> connectedClients;
	
	ClientThread(Socket socket, HashMap<Integer, Socket> connectedClients, BlockingQueue<ApplicationEvent> eventQueue, int id){
		this.socket=socket;
		this.connectedClients=connectedClients;
		this.eventQueue=eventQueue;
		this.id=id;
		
	}
}
