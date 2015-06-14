package pl.krzyszczak.mikolaj.serverchat.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.serverchat.appEvent.*;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendDummy;

/**
 * Klasa w¹tku zajmuj¹cego siê obs³ug¹ danego klienta
 * 
 * @author Miko³aj
 *
 */
public class ClientThread extends Thread
{
	/** referencja do socketu danego klienta */
	Socket socket;
	/** Strumien wejsciowy */
	private ObjectInputStream inStream;
	/** Strumien wyjsciowy */
	private ObjectOutputStream outStream;
	/** referencja do eventQueue */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/**
	 * referencja do mapy przetrzymuj¹cej strumienie wyjœciowe u¿ytkowników,
	 * rozró¿nienie po UserId
	 */
	private HashMap<UserId, ObjectOutputStream> userOutputStreamsMap;

	/**
	 * Konstruktor klasy ClientThread
	 * 
	 * @param socket
	 * @param userOutputStreamsMap
	 * @param eventQueue
	 */
	ClientThread(Socket socket,
			HashMap<UserId, ObjectOutputStream> userOutputStreamsMap,
			BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.socket = socket;
		this.userOutputStreamsMap = userOutputStreamsMap;
		this.eventQueue = eventQueue;

		try
		{
			outStream = new ObjectOutputStream(socket.getOutputStream());
			/** TODO */
			System.out.println(outStream.hashCode());
			inStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e)
		{
			System.err.println("Blad przy tworzeniu strumienia " + e);
			return;
		}
	}

	/**
	 * Metoda override, do uruchamiania nowego w¹tku
	 */
	public void run()
	{
		ApplicationEvent listenedEvent;
		System.out.println("Czekam na akcjê od u¿ytkownika");
		while (true)
		{
			try
			{
				listenedEvent = (ApplicationEvent) inStream.readObject();

				if (listenedEvent instanceof LoginAppClientEvent)
				{
					LoginAppClientEvent loginListenedEvent = (LoginAppClientEvent) listenedEvent;
					LoginAppServerEvent loginAppServerEvent= new LoginAppServerEvent(loginListenedEvent, outStream);
					eventQueue.add(loginAppServerEvent);
				} else if (listenedEvent instanceof CreateAccountAppClientEvent)
				{
					CreateAccountAppClientEvent accountAppClientEvent = (CreateAccountAppClientEvent) listenedEvent;
					CreateAccountAppServerEvent accountAppServerEvent = new CreateAccountAppServerEvent(
							accountAppClientEvent.getName(),
							accountAppClientEvent.getPassword(), outStream);
					eventQueue.add(accountAppServerEvent);

				} else
					eventQueue.add(listenedEvent);

			} catch (IOException e)
			{
				System.err.println(e);
				System.err.println("Klient o ID " + socket.hashCode()
						+ " roz³¹czy³ siê");
				for (UserId userId : userOutputStreamsMap.keySet())
				{
					if (userOutputStreamsMap.get(userId).equals(outStream))
					{
						userOutputStreamsMap.remove(userId);
						break;
					}
				}
				try
				{
					socket.close();
				} 
				catch (IOException e1)
				{
					System.err.println(e1);
				}
				break;

			} catch (ClassNotFoundException e)
			{
				System.err.println(e);
				System.exit(1);
			}

		}
	}

	/**
	 * Metoda wysy³aj¹ca makietê
	 * 
	 * @param sendDummy
	 */
	public void sendMessage(SendDummy sendDummy)
	{
		try
		{
			outStream.writeObject(sendDummy);
		} catch (IOException e)
		{
			System.err.println(e);

		}
	}
}
