/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.clientchat.appEvent.CloseMessageWindowEvent;
import pl.krzyszczak.mikolaj.clientchat.appEvent.NewDummyEvent;
import pl.krzyszczak.mikolaj.clientchat.appEvent.OpenMessageWindowEvent;
import pl.krzyszczak.mikolaj.clientchat.appEvent.SendToServerEvent;
import pl.krzyszczak.mikolaj.clientchat.connection.MainClientClass;
import pl.krzyszczak.mikolaj.clientchat.view.LoginView;
import pl.krzyszczak.mikolaj.clientchat.view.MessageView;
import pl.krzyszczak.mikolaj.clientchat.view.UserView;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendDummy;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendErrorDummy;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendMessageDummy;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendUserContactsDummy;

/**
 * @author Miko�aj
 *
 */
public class Controller
{

	/** Kolejka blokuj�ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/** Referencja do klasy klienta */
	private MainClientClass client;
	/** okno logowania */
	private LoginView loginView;
	/** Okno kontakt�w */
	private UserView userView;
	/** mapa okien rozm�w */
	private HashMap<UserId, MessageView> messageViews;
	/** Mapa strategii obs�ugi Event�w */
	private Map<Class<? extends ApplicationEvent>, ApplicationEventStrategy> eventStrategyMap;
	/** Mapa strategii obs�ugi makiet */
	private Map<Class<? extends SendDummy>, ApplicationDummyStrategy> dummyStrategyMap;

	public Controller(final BlockingQueue<ApplicationEvent> eventQueue,
			final MainClientClass client)
	{
		this.eventQueue = eventQueue;
		this.client = client;
		messageViews = new HashMap<UserId, MessageView>();
		loginView= new LoginView(eventQueue);
		

		eventStrategyMap = new HashMap<Class<? extends ApplicationEvent>, Controller.ApplicationEventStrategy>();
		eventStrategyMap.put(OpenMessageWindowEvent.class,
				new OpenMessageWindowStrategy());
		eventStrategyMap.put(CloseMessageWindowEvent.class,
				new CloseMessageWindowStrategy());
		eventStrategyMap.put(NewDummyEvent.class, new NewDummyStrategy());
		eventStrategyMap.put(SendToServerEvent.class,
				new SendToServerStrategy());
		
		dummyStrategyMap= new HashMap<Class<? extends SendDummy>, Controller.ApplicationDummyStrategy>();
		dummyStrategyMap.put(SendErrorDummy.class, new ErrorDummyStrategy());
		dummyStrategyMap.put(SendMessageDummy.class, new MessageDummyStrategy());
		dummyStrategyMap.put(SendUserContactsDummy.class, new UserContactsDummyStrategy());

	}

	public void work()
	{
		while (true)
		{
			try
			{
				ApplicationEvent applicationEvent = eventQueue.take();
				ApplicationEventStrategy applicationEventStrategy = eventStrategyMap
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
	 * Abstrakcyjna klasa dla mapy strategii do obs�ugi makiet
	 * 
	 * @autor Krzyszczak Miko�aj;
	 */
	abstract class ApplicationDummyStrategy
	{
		/**
		 * Abstrakcyjna metoda opisuj�ca obs�ug� danego zdarzenia
		 * 
		 * @param applicationEvent
		 */
		abstract void execute(final SendDummy sendDummy);
	}

	/**
	 * Klasa opisuj�ca spos�b obs�ugi danej makiety
	 * 
	 * @author Miko�aj
	 *
	 */
	public class UserContactsDummyStrategy extends ApplicationDummyStrategy
	{

		@Override
		void execute(SendDummy sendDummy)
		{
			SendUserContactsDummy userContactsDummy = ((SendUserContactsDummy) sendDummy);
			if (loginView != null)
			{
				client.setUserId(userContactsDummy.getByUserId());
				loginView.disposeLoginView();
				String showMessage= new String ("Poprawne zalogowanie. Tw�j numer ID:" + client.getUserId().getId());
				System.out.println(showMessage);
				userView=new UserView(eventQueue, client.getUserId());
				userView.setVisibleLobbyView(true);
				userView.displayInfoMessage(showMessage);
				loginView = null;
			}
			userView.setContacts(userContactsDummy.getAllUsers(),
					userContactsDummy.getUsersContacts());

		}

	}

	public class ErrorDummyStrategy extends ApplicationDummyStrategy
	{

		void execute(SendDummy sendDummy)
		{
			SendErrorDummy errorDummy= ((SendErrorDummy) sendDummy);
			if (loginView!=null)
				loginView.displayInfoMessage(errorDummy.getErrorMessage());
			else
				userView.displayInfoMessage(errorDummy.getErrorMessage());			
		}
	}
	
	class MessageDummyStrategy extends ApplicationDummyStrategy
	{

		/**TODO*/
		@Override
		void execute(SendDummy sendDummy)
		{
			SendMessageDummy messageDummy=((SendMessageDummy)sendDummy);
			UserId withUserId=messageDummy.getToUserId();
			MessageView messageView=null;
			for(UserId userId: messageViews.keySet())
			{
				if(userId.equals(withUserId))
					{
					messageView= messageViews.get(userId);
					break;					
					}
			}
			if(messageView==null)
			{
				messageViews.put(withUserId, new MessageView(eventQueue, withUserId));
				messageView=messageViews.get(withUserId);
			}
			messageView.setMessageList(messageDummy.getListOfMessages());
			
			
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
	 * Klasa strategii ob�uguj�cej event zwi�zany z wys�aniem wiadomo�ci do
	 * serwera
	 * 
	 * @author Miko�aj
	 *
	 */
	class SendToServerStrategy extends ApplicationEventStrategy
	{

		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			SendToServerEvent sendToServerEvent = ((SendToServerEvent) applicationEvent);
			UserId byUserId = client.getUserId();
			sendToServerEvent.setByUserId(byUserId);
			client.sendEvent(sendToServerEvent.getApplicationEvent());

		}

	}

	/**
	 * @author Miko�aj
	 *
	 */
	class NewDummyStrategy extends ApplicationEventStrategy
	{

		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			NewDummyEvent newDummyEvent = ((NewDummyEvent) applicationEvent);
			ApplicationDummyStrategy applicationDummyStrategy = dummyStrategyMap
					.get(newDummyEvent.getDummy().getClass());
			applicationDummyStrategy.execute(newDummyEvent.getDummy());

		}

	}

	/**
	 * 
	 * Klasa opisuj�ca stategi� ob�ugi eventu zamkni�cia rozmowy z danym
	 * u�ytkownikiem
	 * 
	 * @author Miko�aj
	 *
	 */
	class CloseMessageWindowStrategy extends ApplicationEventStrategy
	{

		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			CloseMessageWindowEvent messageWindowEvent = ((CloseMessageWindowEvent) applicationEvent);
			UserId toUserId = messageWindowEvent.getUserId();
			for(UserId userId: messageViews.keySet())
			{
				if(userId.equals(toUserId))
					messageViews.remove(userId);
			}
		}

	}

	/**
	 * Klasa opisuj�ca strategi� ob�ugi eventu utworzenia konta rozmowy z nowym
	 * u�ytkownikiem
	 * 
	 * @author Miko�aj
	 *
	 */
	class OpenMessageWindowStrategy extends ApplicationEventStrategy
	{

		@Override
		void execute(ApplicationEvent applicationEvent)
		{
			OpenMessageWindowEvent messageWindowEvent = ((OpenMessageWindowEvent) applicationEvent);
			UserId toUserId = messageWindowEvent.getToUserId();
			if (!messageViews.containsKey(toUserId))
				messageViews
						.put(toUserId, new MessageView(eventQueue, toUserId));

		}

	}

}
