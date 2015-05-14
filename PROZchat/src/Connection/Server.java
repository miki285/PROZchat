package Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import javax.sound.midi.SysexMessage;

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
	//unikalneID
	private int uniqueID;
	//podtrzymywanie dzia³ania
	private boolean keepGoing;
	//Wyœwietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	// referencja na kolejkê eventów
	private BlockingQueue<ApplicationEvent> eventQueue;
	private ServerSocket serverSocket;
	
	public Server (BlockingQueue<ApplicationEvent> eventQueue, int port){
		this.port = port;
		this.eventQueue=eventQueue;
		connectedClients=new HashMap<Integer, Socket>();
		//Tworzenie serwera
		connect();
		//W³aczenie nas³uchiwania
		run();
	}
	
	//Tworzy serwer na danym sockecie
	private void connect(){
		try{
			this.serverSocket=new ServerSocket(port);
			keepGoing=true;			
		}
		catch(IOException e){
			System.err.println("Nie mozna utworzyæ serwera" +e);
			System.exit(1);
		}
		
		
	}
	//Nas³uchiwanie
	private void run(){
		while(keepGoing){
			try{
				Socket clientSocket= serverSocket.accept();
				display("Serwer czeka na klientów na porcie: " + port +" .");
				if(!keepGoing)
					closeConnect();
				uniqueID++;
				ClientThread clientThread=new ClientThread( clientSocket, connectedClients, eventQueue, uniqueID);
				clientThread.start();	
			}
			catch(IOException e){
				System.err.println(e);
			}
		}
	}
	//Koñczenie pracy serwera
	private void closeConnect(){
		try{
			this.serverSocket.close();
			this.keepGoing=false;
		}
		catch(IOException e)
		{
			System.err.println("Blad przy rozlaczaniu serwera: "+ e);
			System.exit(1);
		}
	}
	//Usuwanie klienta po jego id
	private void remove(int id){
		connectedClients.remove(id);
		//clientThread.remove(id); to do!
	}
	
	//zwracaj¹ca mape klientów
	
	public void getClient(){} //rozkminiæ
	
	
	//metoda do wyœwietlania wiadomosci z serwera razem z dat¹
	private void display(String msg){
	String time=simpleDateFormat.format(new Date()) + " " + msg;
	System.out.println(time);
	}
	
	
}
