package connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import appEvent.ApplicationEvent;
import Helpfull.UserId;

public class MainServerClass {
	/* Numer portu na którym nasłuchuje serwer */
	private final int portNumber;
	/*Mapa przetrzymująca strumienie wyjściowe użytkowników, rozróżnienie po UserId*/
	private HashMap<UserId, ObjectOutputStream> userOutputStreamsMap;
	/* Kolejka blokująca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/* Port nasłuchiwania serwera */
	private ServerSocket serverSocket;
	//Wyświetlanie czasu
	private SimpleDateFormat simpleDateFormat;
	
	
	/*
	 * Konstruktor głównej klasy serwera
	 * @param numer portu do nasłuchiwania i referencja do eventQueue
	 */
	public MainServerClass(int portNumber, BlockingQueue<ApplicationEvent> eventQueue )
	{
		this.eventQueue=eventQueue;
		this.portNumber=portNumber;
		this.userOutputStreamsMap= new HashMap<UserId, ObjectOutputStream>();
		this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		
		connectionCreate();
		ConnectionListener connectionListener=new ConnectionListener(this.serverSocket, this.eventQueue, this.userOutputStreamsMap);
		connectionListener.start();
		
	}
	
	
	/*
	 * Metoda tworząca serwer na danym porcie
	 */
	private void connectionCreate(){
		try{
			this.serverSocket=new ServerSocket(portNumber);			
		}
		catch(IOException e){
			System.err.println("Nie mozna utworzyć serwera" +e);
			System.exit(1);
		}
		display("Serwer nasłuchuje użytkowników na porcie : " + portNumber +" .");
		
	}
	
	
	/*
	 * Metoda do wyłączania serwera
	 */
	public void closeConnection(){
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			System.err.println("Bład podczas zamykania połączenia" + e);
			System.exit(1);
		}
	}
	
	
	/*
	 * Metoda do wysyłania wiadomości przez serwer
	 * @param userId
	 * @
	 */
//    @TODO !!!!!!!!!!!!!!!!!!!!!!
	
	
	//metoda do wyświetlania wiadomosci z serwera razem z datą
		private void display(String msg){
		String time=simpleDateFormat.format(new Date()) + " " + msg;
		System.out.println(time);
		}
}
