package Connection;

import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import AplicationEvent.ApplicationEvent;
/*
 * Klasa serwera odbierającego i wysyłającego pakeity klientów, pomocnicza klasa ClientThread.
 * @autor: Krzyszczak Mikołaj
 */
public class Server {
	// Mapa klientów z socketami na podstawie ich id
	private HashMap<Integer, Socket> connectedClients;
	//numer portu
	private int port;
	//podtrzymywanie działania
	private boolean keepGoing;
	//Wyświetlanie czasu
	private SimpleDateFormat simpleDateFormat;
	//kolejka eventów
	private BlockingQueue<ApplicationEvent> eventQueue;
	
	public Server (BlockingQueue<ApplicationEvent> eventQueue, int port){
		this.port = port;
		this.eventQueue=eventQueue;
		connectedClients=new HashMap<Integer, Socket>();
	}
	
	//metoda do wyświetlania wiadomosci z serwera razem z datą
	private void diplay(String msg){
	String time=simpleDateFormat.format(new Date()) + " " + msg;
	System.out.println(time);
	}
}
