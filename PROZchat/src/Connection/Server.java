package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.concurrent.BlockingQueue;




import SendDummy.SendDummy;
import AplicationEvent.ApplicationEvent;
/*
 * Klasa serwera odbieraj¹cego i wysy³aj¹cego pakeity klientów, pomocnicza klasa ClientThread.
 * @autor: Krzyszczak Miko³aj
 */
public class Server {
	// Mapa klientów z socketami na podstawie ich id
	private HashMap<Integer, Socket> connectedClients;
	private HashMap<Integer, ClientThread> listenedClients;
	//numer portu
	private int port;
	//Wyœwietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	// referencja na kolejkê eventów
	private BlockingQueue<ApplicationEvent> eventQueue;
	private ServerSocket serverSocket;
	
	public Server (BlockingQueue<ApplicationEvent> eventQueue, int port){
		this.port = port;
		this.eventQueue=eventQueue;
		this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		this.connectedClients=new HashMap<Integer, Socket>();
		this.listenedClients= new HashMap<Integer, ClientThread>();
		//Tworzenie serwera
		connectCreate();
		//W³aczenie nas³uchiwania
		ConnectionListener connectionListener= new ConnectionListener(this.serverSocket, this.eventQueue,this.connectedClients,this.listenedClients);
		connectionListener.start();
	}
	
	//Tworzy serwer na danym sockecie
	private void connectCreate(){
		try{
			this.serverSocket=new ServerSocket(port);			
		}
		catch(IOException e){
			System.err.println("Nie mozna utworzyæ serwera" +e);
			System.exit(1);
		}
		display("Serwer czeka na klientów na porcie: " + port +" .");
		
	}

	//Koñczenie pracy serwera
	public void closeConnect(){
		try{
			this.serverSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Blad przy rozlaczaniu serwera: "+ e);
			System.exit(1);
		}
	}
	//Usuwanie klienta po jego id
	public void removeClient(int id){
		connectedClients.remove(id);
		//clientThread.remove(id); to do!
	}
	
	//zwracaj¹ca mape klientów
	
	public HashMap<Integer, Socket> getClient(){
		return connectedClients;
	} 
	
	public ServerSocket getSocket() {
		return this.serverSocket;
	}
	
	public boolean addClient(int id, Socket socket){
		if(this.connectedClients.containsValue(id)){
			return false;
		}
		else{
			this.connectedClients.put(id, socket);
			return true;
		}
		
	}
	
	
	/*
	 * Metoda wysy³aj¹ca makietê do danego gracza
	 * @param id klienta
	 * @param makieta do wys³ania
	 */
	public void sendToClient(int id, SendDummy sendDummy ) {
		listenedClients.get(id).sendMessage(sendDummy);
	}
	
	
	
	
	
	
	
	
	
	
	//metoda do wyœwietlania wiadomosci z serwera razem z dat¹
	private void display(String msg){
	String time=simpleDateFormat.format(new Date()) + " " + msg;
	System.out.println(time);
	}
	
	
}
