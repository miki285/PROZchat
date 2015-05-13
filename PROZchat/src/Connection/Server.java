package Connection;

import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import AplicationEvent.ApplicationEvent;
/*
 * Klasa serwera odbieraj¹cego i wysy³aj¹cego pakeity klientów, pomocnicza klasa ClientThread.
 * @autor: Krzyszczak Miko³aj
 */
public class Server {
	// Mapa klientów z socketami na podstawie ich id
	private HashMap<Integer, Socket> connectedClients;
	//numer portu
	private int port;
	//podtrzymywanie dzia³ania
	private boolean keepGoing;
	//Wyœwietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	//kolejka eventów
	private BlockingQueue<ApplicationEvent> eventQueue;
	
	public Server (BlockingQueue<ApplicationEvent> eventQueue, int port){
		this.port = port;
		this.eventQueue=eventQueue;
		connectedClients=new HashMap<Integer, Socket>();
	}
	
	//metoda do wyœwietlania wiadomosci z serwera razem z dat¹
	private void diplay(String msg){
	String time=simpleDateFormat.format(new Date()) + " " + msg;
	System.out.println(time);
	}
}
