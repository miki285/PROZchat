package pl.krzyszczak.mikolaj.serverchat.controler;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.serverchat.appEvent.*;
import pl.krzyszczak.mikolaj.serverchat.connection.MainServerClass;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;
import pl.krzyszczak.mikolaj.serverchat.model.Message;
import pl.krzyszczak.mikolaj.serverchat.model.Model;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.DataMessage;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.InfoMessage;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.ModelMessages;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendErrorDummy;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendMessageDummy;
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
		strategyMap.put(CreateAccountAppServerEvent.class,
				new ButtonCreateAccountClickedEventStrategy());
		strategyMap.put(LoginAppServerEvent.class,
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
						model.getUserId(byUserId),
						new SendUserContactsDummy(
								(HashSet<UsersDataForClient>) ((DataMessage) message)
										.getData(), model.getAllUsersSet(),
								byUserId));
			}

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
		 * 
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

			/** TODO */
			System.out.println(outputStream.hashCode());

			UserId userId = model.setUniqueId();
			model.addUser(userId, userName, userPassword);
			server.addNewUsersStreams(userId, outputStream);
			SendUserContactsDummy sendUserContactsDummy = new SendUserContactsDummy(
					new HashSet<UsersDataForClient>(), model.getAllUsersSet(), userId);
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
			UserId userId = ((LoginAppServerEvent) applicationEvent).getId();
			UserPassword password = ((LoginAppServerEvent) applicationEvent)
					.getPassword();
			ObjectOutputStream objectOutputStream= ((LoginAppServerEvent) applicationEvent).getObjectOutputStream();
			ModelMessages message = model.checkUserPassword(userId, password);

			
			if (message instanceof InfoMessage)
			{
				server.addNewUsersStreams(userId, objectOutputStream);
				server.sendDummy(userId, new SendErrorDummy(
						((InfoMessage) message).getInfoMessage()));
				server.removeNewUsersStreams(userId);
			} else
			{
				UserId byUserId=model.getUserId(userId);
				server.addNewUsersStreams(byUserId, objectOutputStream);
				server.sendDummy(
						byUserId,
						new SendUserContactsDummy(
								(HashSet<UsersDataForClient>) ((DataMessage) message)
										.getData(), model.getAllUsersSet(),
								userId));
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

		@SuppressWarnings("unchecked")
		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			ModelMessages message = model
					.addUserMessage((MessageAppEvent) applicationEvent);
			UserId byUserId = ((MessageAppEvent) applicationEvent).getByUser();
			UserId toUserId = ((MessageAppEvent) applicationEvent).getToUser();
			if (message instanceof InfoMessage)
			{
				server.sendDummy(byUserId, new SendErrorDummy(
						((InfoMessage) message).getInfoMessage()));
			} else
			{
				SendMessageDummy messageDummy = new SendMessageDummy(
						(ArrayList<Message>) ((DataMessage) message).getData());
				messageDummy.setToUserId(byUserId);
				server.sendDummy(model.getUserId(toUserId), messageDummy);
				messageDummy.setToUserId(toUserId);
				server.sendDummy(model.getUserId(byUserId), messageDummy);

			}

		}
	}

}
