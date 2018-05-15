package pl.krzyszczak.mikolaj.clientchat.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.clientchat.appEvent.NewDummyEvent;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendDummy;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendUserContactsDummy;

/**
 * G³ówna klasa aplikacji klienta, s³u¿y do komunikacji z serwerem, wysy³aniem
 * danych eventów oraz odbierania od serwera odpowiednich makiet
 * 
 * @autor Krzyszczak Miko³aj
 */
public class MainClientClass
{
	/** Adres serwera */
	private String serverAddress;
	/** numer portu serwera */
	private int port;
	/** Socket klienta */
	private Socket socket;
	/** UserID danego klienta */
	private UserId userId;

	/** Strumien wejsciowy */
	private ObjectInputStream inStream;
	/** Strumien wyjsciowy */
	private ObjectOutputStream outStream;

	/** Kolejka blokuj¹ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;

	/** Wyœwietlanie czasu */
	private SimpleDateFormat simpleDateFormat;

	/**
	 * konstruktor klasy clienta
	 */
	public MainClientClass(final String serverAddress, final int port,
			final BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.serverAddress = serverAddress;
		this.port = port;
		this.eventQueue = eventQueue;
		this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

		this.run();
	}

	/** Metoda w³¹czaj¹ca klienta */

	private void run()
	{
		display("Klient próbuje nawi¹zaæ po³¹czenie z serwerem");

		try
		{
			this.socket = new Socket(this.serverAddress, this.port);
			this.inStream = new ObjectInputStream(socket.getInputStream());
			this.outStream = new ObjectOutputStream(socket.getOutputStream());

			ServerListener serverListener = new ServerListener();
			serverListener.start();

		} catch (IOException e)
		{
			System.exit(1);
		}

	}

	/** Metoda zwracaj¹ca wartoœæ UserId */
	public UserId getUserId()
	{
		return userId;
	}

	/**
	 * Metoda ustawiaj¹ca UserId nadane przez serwer
	 */
	public void setUserId(UserId userId)
	{
		this.userId = userId;
	}

	/**
	 * Metoda wysy³aj¹ca wiadomoœæ o evencie do serwera
	 */
	public void sendEvent(ApplicationEvent applicationEvent)
	{

		try
		{
			outStream.writeObject(applicationEvent);
		} catch (IOException e)
		{
			System.exit(1);
		}

	}

	/**
	 * Metoda do wyœwietlania wiadomosci razem z dat¹
	 */
	private void display(String msg)
	{
		String time = simpleDateFormat.format(new Date()) + " " + msg;
		System.out.println(time);
	}

	/**
	 * Klasa nas³uchuj¹ca po³aczeñ od serwera obs³ugê widoki na podstawie
	 * otrzymanej makiety TODO to jest Ÿle~!!
	 */
	public class ServerListener extends Thread
	{

		public void run()
		{
			display("Klient nas³uchuje wiadomoœci");
			while (true)
			{
				try
				{
					SendDummy dummy = (SendDummy) inStream.readObject();
					eventQueue.offer(new NewDummyEvent(dummy));
				} catch (IOException | ClassNotFoundException e)
				{
					e.printStackTrace();
					System.exit(2);
				}
			}
		}

	}
}
