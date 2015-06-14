package pl.krzyszczak.mikolaj.serverchat.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendDummy;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendUserContactsDummy;

/**
 * G��wna klasa serwera
 * 
 * @author Miko�aj
 *
 */
public class MainServerClass
{
	/** Numer portu na kt�rym nas�uchuje serwer */
	private final int portNumber;
	/**
	 * Mapa przetrzymuj�ca strumienie wyj�ciowe u�ytkownik�w, rozr�nienie po
	 * UserId
	 */
	private HashMap<UserId, ObjectOutputStream> userOutputStreamsMap;
	/** Kolejka blokuj�ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/** Port nas�uchiwania serwera */
	private ServerSocket serverSocket;
	/** Wy�wietlanie czasu */
	private SimpleDateFormat simpleDateFormat;

	/**
	 * Konstruktor g��wnej klasy serwera
	 * 
	 * @param numer
	 *            portu do nas�uchiwania i referencja do eventQueue
	 */
	public MainServerClass(int portNumber,
			BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
		this.portNumber = portNumber;
		this.userOutputStreamsMap = new HashMap<UserId, ObjectOutputStream>();
		this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

		connectionCreate();
		ConnectionListener connectionListener = new ConnectionListener(
				this.serverSocket, this.eventQueue, this.userOutputStreamsMap);
		connectionListener.start();

	}

	/**
	 * Metoda tworz�ca serwer na danym porcie
	 */
	private void connectionCreate()
	{
		try
		{
			this.serverSocket = new ServerSocket(portNumber);
		} catch (IOException e)
		{
			System.err.println("Nie mozna utworzy� serwera" + e);
			System.exit(1);
		}
		display("Serwer nas�uchuje u�ytkownik�w na porcie : " + portNumber
				+ " .");

	}

	/**
	 * Metoda do wy��czania serwera
	 */
	public void closeConnection()
	{
		try
		{
			this.serverSocket.close();
		} catch (IOException e)
		{
			System.err.println("B�ad podczas zamykania po��czenia" + e);
			System.exit(1);
		}
	}

	/**
	 * Metoda do wysy�ania wiadomo�ci przez serwer
	 * 
	 * @param userId
	 * @param dummy
	 */
	public void sendDummy(UserId userId, SendDummy dummy)
	{

		try
		{
			
			
			ObjectOutputStream objectOutputStream = userOutputStreamsMap.get(userId);
			if (objectOutputStream!=null)
				objectOutputStream.writeObject(dummy);
		
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Metoda wysy�aj�ca makiety do wszystkich klient�w
	 * 
	 * @param dummy
	 */
	public void sendToAllDummy(SendDummy dummy)
	{

		try
		{
			for (ObjectOutputStream objectOutputStream : userOutputStreamsMap
					.values())

			{
				objectOutputStream.writeObject(dummy);
			}

		}  catch (IOException e)
		{
			System.out.println("asdsfsadfsdfs");
			System.err.println(e);

		}

	}

	/**
	 * Metoda s�u��ca do dodania nowopowsta�uch kont do hashsetu outputStream�w
	 */
	public void addNewUsersStreams(UserId userId,
			ObjectOutputStream objectOutputStream)
	{
		userOutputStreamsMap.put(userId, objectOutputStream);
	}
	
	/**
	 * Metoda s�u��ca do kasowania nowopowsta�uch kont do hashsetu outputStream�w
	 */
	public void removeNewUsersStreams(UserId userId)
	{
		userOutputStreamsMap.remove(userId);
	}

	/**
	 * metoda do wy�wietlania wiadomosci z serwera razem z dat�
	 * 
	 * @param msg
	 */
	private void display(String msg)
	{
		String time = simpleDateFormat.format(new Date()) + " " + msg;
		System.out.println(time);
	}
}
