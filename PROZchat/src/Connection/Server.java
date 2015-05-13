package Connection;

import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import AplicationEvent.ApplicationEvent;
/*
 * Klasa serwera odbieraj�cego i wysy�aj�cego pakeity klient�w, pomocnicza klasa ClientThread.
 * @autor: Krzyszczak Miko�aj
 */
public class Server {
	// Mapa klient�w z socketami na podstawie ich id
	private HashMap<Integer, Socket> connectedClients;
	//numer portu
	private int port;
	//podtrzymywanie dzia�ania
	private boolean keepGoing;
	//Wy�wietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	//kolejka event�w
	private BlockingQueue<ApplicationEvent> eventQueue;
	
	public Server (BlockingQueue<ApplicationEvent> eventQueue, int port){
		this.port = port;
		this.eventQueue=eventQueue;
		connectedClients=new HashMap<Integer, Socket>();
	}
	
	//metoda do wy�wietlania wiadomosci z serwera razem z dat�
	private void diplay(String msg){
	String time=simpleDateFormat.format(new Date()) + " " + msg;
	System.out.println(time);
	}
}
