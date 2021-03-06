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
 * Klasa służąca do nasłuchiwania przychodzących połączeń od klientów
 * 
 * @autor Krzyszczak Mikołaj
 */
public class ConnectionListener extends Thread
{

	/** Server socket */
	private final ServerSocket serverSocket;
	/** Kolejka blokująca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/**
	 * Mapa przetrzymująca strumienie wyjściowe użytkowników, rozróżnienie po
	 * UserId
	 */
	private HashMap<UserId, ObjectOutputStream> userOutputStreamsMap;

	/**
	 * Konstruktor klasy connectionListener
	 */
	public ConnectionListener(final ServerSocket serverSocket,
			BlockingQueue<ApplicationEvent> eventQueue,
			HashMap<UserId, ObjectOutputStream> userOutputStreamMap)
	{
		this.serverSocket = serverSocket;
		this.eventQueue = eventQueue;
		this.userOutputStreamsMap = userOutputStreamMap;
	}

	/**
	 * Metoda klasy connectionListener która nasłuchuje połączeń od nowych
	 * klientów
	 */
	public void run()
	{
		while (true)
		{
			try
			{
				Socket usrSocket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(usrSocket,
						userOutputStreamsMap, eventQueue);
				clientThread.start();
			} catch (IOException e)
			{
				System.err.println(e);
			}
		}

	}

}
