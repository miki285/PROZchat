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
	/* Numer portu na którym nas³uchuje serwer */
	private final int portNumber;
	/*Mapa przetrzymuj¹ca strumienie wyjœciowe u¿ytkowników, rozró¿nienie po UserId*/
	private HashMap<UserId, ObjectOutputStream> userOutputStreamsMap;
	/* Kolejka blokuj¹ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/* Port nas³uchiwania serwera */
	private ServerSocket serverSocket;
	//Wyœwietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	
	
	/*
	 * Konstruktor g³ównej klasy serwera
	 * @param numer portu do nas³uchiwania i referencja do eventQueue
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
	 * Metoda tworz¹ca serwer na danym porcie
	 */
	private void connectionCreate(){
		try{
			this.serverSocket=new ServerSocket(portNumber);			
		}
		catch(IOException e){
			System.err.println("Nie mozna utworzyæ serwera" +e);
			System.exit(1);
		}
		display("Serwer nas³uchuje u¿ytkowników na porcie : " + portNumber +" .");
		
	}
	
	
	/*
	 * Metoda do wy³¹czania serwera
	 */
	public void closeConnection(){
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			System.err.println("B³ad podczas zamykania po³¹czenia" + e);
			System.exit(1);
		}
	}
	
	
	/*
	 * Metoda do wysy³ania wiadomoœci przez serwer
	 * @param userId
	 * @
	 */
//    @TODO !!!!!!!!!!!!!!!!!!!!!!
	
	
	//metoda do wyœwietlania wiadomosci z serwera razem z dat¹
		private void display(String msg){
		String time=simpleDateFormat.format(new Date()) + " " + msg;
		System.out.println(time);
		}
}
