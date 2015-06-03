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
 * Klasa s�u��ca do nas�uchiwania przychodz�cych po��cze� od klient�w
 * 
 * @autor Krzyszczak Miko�aj
 */
public class ConnectionListener extends Thread {

	/** Server socket */
	private final ServerSocket serverSocket;
	/** Kolejka blokuj�ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/**
	 * Mapa przetrzymuj�ca strumienie wyj�ciowe u�ytkownik�w, rozr�nienie po
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
	 * Metoda klasy connectionListener kt�ra nas�uchuje po��cze� od nowych
	 * klient�w
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
