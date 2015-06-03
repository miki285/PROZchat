package pl.krzyszczak.mikolaj.serverchat.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.serverchat.appEvent.*;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * Klasa s³u¿¹ca do nas³uchiwania przychodz¹cych po³¹czeñ od klientów
 * 
 * @autor Krzyszczak Miko³aj
 */
public class ConnectionListener extends Thread {

	/** Server socket */
	private final ServerSocket serverSocket;
	/** Kolejka blokuj¹ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/**
	 * Mapa przetrzymuj¹ca strumienie wyjœciowe u¿ytkowników, rozró¿nienie po
	 * UserId
	 */
	private HashMap<UserId, ObjectOutputStream> userOutputStreamsMap;

	/**
	 * Konstruktor klasy connectionListener
	 */
	public ConnectionListener(final ServerSocket serverSocket,
			BlockingQueue<ApplicationEvent> eventQueue,
			HashMap<UserId, ObjectOutputStream> userOutputStreamMap) {
		this.serverSocket = serverSocket;
		this.eventQueue = eventQueue;
		this.userOutputStreamsMap = userOutputStreamMap;
	}

	/**
	 * Metoda klasy connectionListener która nas³uchuje po³¹czeñ od nowych
	 * klientów
	 */
	public void run() {
		while (true) {
			try {
				Socket usrSocket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(usrSocket,
						userOutputStreamsMap, eventQueue);
				clientThread.start();
			} catch (IOException e) {
				System.err.println(e);
			}
		}

	}

}
