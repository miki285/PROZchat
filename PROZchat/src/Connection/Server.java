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
 * Klasa serwera odbieraj�cego i wysy�aj�cego pakeity klient�w, pomocnicza klasa ClientThread.
 * @autor: Krzyszczak Miko�aj
 */
public class Server {
	// Mapa klient�w z socketami na podstawie ich id
	private HashMap<Integer, Socket> connectedClients;
	private HashMap<Integer, ClientThread> listenedClients;
	//numer portu
	private int port;
	//Wy�wietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	// referencja na kolejk� event�w
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
		//W�aczenie nas�uchiwania
		ConnectionListener connectionListener= new ConnectionListener(this.serverSocket, this.eventQueue,this.connectedClients,this.listenedClients);
		connectionListener.start();
	}
	
	//Tworzy serwer na danym sockecie
	private void connectCreate(){
		try{
			this.serverSocket=new ServerSocket(port);			
		}
		catch(IOException e){
			System.err.println("Nie mozna utworzy� serwera" +e);
			System.exit(1);
		}
		display("Serwer czeka na klient�w na porcie: " + port +" .");
		
	}

	//Ko�czenie pracy serwera
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
	
	//zwracaj�ca mape klient�w
	
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
	 * Metoda wysy�aj�ca makiet� do danego gracza
	 * @param id klienta
	 * @param makieta do wys�ania
	 */
	public void sendToClient(int id, SendDummy sendDummy ) {
		listenedClients.get(id).sendMessage(sendDummy);
	}
	
	
	
	
	
	
	
	
	
	
	//metoda do wy�wietlania wiadomosci z serwera razem z dat�
	private void display(String msg){
	String time=simpleDateFormat.format(new Date()) + " " + msg;
	System.out.println(time);
	}
	
	
}
