package pl.krzyszczak.mikolaj.serverchat.controler;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.serverchat.appEvent.*;
import pl.krzyszczak.mikolaj.serverchat.connection.MainServerClass;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;
import pl.krzyszczak.mikolaj.serverchat.model.Model;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.DataMessage;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.InfoMessage;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.ModelMessages;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendErrorDummy;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendUserContactsDummy;

/**
 * Klasa kontrolera
 * 
 * @autor Krzyszczak Miko��j
 */
public class Controller
{
	/** Kolejka blokuj�ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/** Referencja do modelu */
	private Model model;
	/** referencja do serwera */
	private MainServerClass server;
	/** Mapa strategii obs�ugi zdarze� */
	private Map<Class<? extends ApplicationEvent>, ApplicationEventStrategy> strategyMap;

	/**
	 * Konstruktor kontrolera
	 * 
	 * @param eventQueue
	 * @param model
	 * @param server
	 */
	public Controller(final BlockingQueue<ApplicationEvent> eventQueue,
			final Model model, final MainServerClass server)
	{
		this.eventQueue = eventQueue;
		this.model = model;
		this.server = server;

		strategyMap = new HashMap<Class<? extends ApplicationEvent>, Controller.ApplicationEventStrategy>();
		strategyMap.put(AddFriendAppEvent.class,
				new ButtonAddFriendUserClickedEventStrategy());
		strategyMap.put(ClearHistoryAppEvent.class,
				new ButtonClearHistoryClickedEventStrategy());
		strategyMap.put(CreateAccountAppServerEvent.class,
				new ButtonCreateAccountClickedEventStrategy());
		strategyMap.put(LoginAppClientEvent.class,
				new ButtonLoginClickedEventStrategy());
		strategyMap.put(MessageAppEvent.class,
				new ButtonSendMessageEventStrategy());
	}

	/** g��wna metoda kontrolera, czeka on na zdarzenia, a nast�pie je obs�uguje */
	public void work()
	{
		while (true)
		{
			try
			{
				ApplicationEvent applicationEvent = eventQueue.take();
				ApplicationEventStrategy applicationEventStrategy = strategyMap
						.get(applicationEvent.getClass());
				applicationEventStrategy.execute(applicationEvent);
			} catch (InterruptedException e)
			{
				/*
				 * nic nie robimy bo w�tek i tak ma by� zawieszony czekaj�c na
				 * zg�oszony Event
				 */
			}

		}
	}

	/**
	 * Abstrakcyjna klasa dla mapy strategii do obs�ugi zdarze�
	 * 
	 * @autor Krzyszczak Miko�aj;
	 */
	abstract class ApplicationEventStrategy
	{
		/**
		 * Abstrakcyjna metoda opisuj�ca obs�ug� danego zdarzenia
		 * 
		 * @param applicationEvent
		 */
		abstract void execute(final ApplicationEvent applicationEvent);
	}

	/**
	 * Klasa opisuj�ca spos�b obs�ugi eventu zwi�zanego z dodaniem przyjaciela
	 * do danego u�ytkownika
	 * 
	 * @author Miko�aj
	 *
	 */
	class ButtonAddFriendUserClickedEventStrategy extends
			ApplicationEventStrategy
	{

		@SuppressWarnings("unchecked")
		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			UserId byUserId = ((AddFriendAppEvent) applicationEvent)
					.getByUser();
			UserId whichUserId = ((AddFriendAppEvent) applicationEvent)
					.getWhichUser();
			ModelMessages message = model.addUserContact(byUserId, whichUserId);
			if (message instanceof InfoMessage)
			{
				server.sendDummy(byUserId, new SendErrorDummy(
						((InfoMessage) message).getInfoMessage()));
			} else if (message instanceof DataMessage)
			{
				server.sendDummy(
						byUserId,
						new SendUserContactsDummy(
								(HashSet<UsersDataForClient>) ((DataMessage) message)
										.getData(), model.getAllUsersSet()));
			}

		}
	}

	class ButtonClearHistoryClickedEventStrategy extends
			ApplicationEventStrategy
	{

		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			// TODO Auto-generated method stub

		}
	}

	/**
	 * Klasa opisuj�ca obs�ug� eventu zwi�zanego z utworzeniem nowego konta
	 * 
	 * @author Miko�aj
	 *
	 */
	class ButtonCreateAccountClickedEventStrategy extends
			ApplicationEventStrategy
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see pl.krzyszczak.mikolaj.serverchat.controler.Controller.
		 * ApplicationEventStrategy
		 * #execute(pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent)
		 */
		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			String userName = ((CreateAccountAppServerEvent) applicationEvent)
					.getName();
			UserPassword userPassword = ((CreateAccountAppServerEvent) applicationEvent)
					.getPassword();
			ObjectOutputStream outputStream = ((CreateAccountAppServerEvent) applicationEvent)
					.getObjectOutputStream();
			
			/** TODO*/
			System.out.println(outputStream.hashCode());
			
			UserId userId = model.setUniqueId();
			model.addUser(userId, userName, userPassword);
			server.addNewUsersStreams(userId, outputStream);
			SendUserContactsDummy sendUserContactsDummy= new SendUserContactsDummy(model.getAllUsersSet(), model
					.getAllUsersSet());
			server.sendToAllDummy(sendUserContactsDummy);

		}
	}

	/**
	 * Klasa opisuj�ca obs�ug� eventu zwi�zanego z zalogowaniem si� u�ytkownika
	 * do serwera
	 * 
	 * @author Miko�aj
	 *
	 */
	class ButtonLoginClickedEventStrategy extends ApplicationEventStrategy
	{

		@SuppressWarnings("unchecked")
		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			UserId userId = ((LoginAppClientEvent) applicationEvent).getId();
			UserPassword password = ((LoginAppClientEvent) applicationEvent)
					.getPassword();

			ModelMessages message = model.checkUserPassword(userId, password);

			if (message instanceof InfoMessage)
			{
				server.sendDummy(userId, new SendErrorDummy(
						((InfoMessage) message).getInfoMessage()));
			} else
			{
				server.sendDummy(
						userId,
						new SendUserContactsDummy(
								(HashSet<UsersDataForClient>) ((DataMessage) message)
										.getData(), model.getAllUsersSet()));
			}
		}

	}

	/**
	 * Klasa opisuj�ca obs�ug� eventu zwi�zanego z wys�aniem wiadomo�ci
	 * 
	 * @author Miko�aj
	 *
	 */
	class ButtonSendMessageEventStrategy extends ApplicationEventStrategy
	{

		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			// TODO Auto-generated method stub

		}
	}

}
