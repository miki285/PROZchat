package controler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import AplicationEvent.*;
import Connection.Server;
import Model.Model;

public class Controller {
	//Kolejka blokuj¹ca eventy
	private BlockingQueue<ApplicationEvent> eventQueue;
	//Referencja do modelu
    private Model model;
	// referencja do serwera
	private Server server;
	//Mapa strategii obs³ugi zdarzeñ
	private Map<Class<? extends ApplicationEvent>, ApplicationEventStrategy> strategyMap;
	
	/*
	 * Konstruktor kontrolera
	 */
	public Controller(final BlockingQueue<ApplicationEvent> eventQueue, final Model model, final Server server){
		this.eventQueue=eventQueue;
		this.model=model;
		this.server=server;
		
		strategyMap= new HashMap<Class<? extends ApplicationEvent>, Controller.ApplicationEventStrategy>();
		strategyMap.put(ButtonActiveUsersEvent.class, new ButtonActiveUsersClickedEventStrategy());
		strategyMap.put(ButtonClearHistoryEvent.class, new ButtonClearHistoryClickedEventStrategy());
		strategyMap.put(ButtonFriendUserEvent.class, new ButtonFriendUserClickedEventStrategy());
		strategyMap.put(ButtonLoginClickedEvent.class, new ButtonLoginClickedEventStrategy());
		strategyMap.put(ButtonSendMessageEvent.class, new ButtonSendMessageEventStrategy());
		strategyMap.put(ButtonWriteToUserEvent.class, new ButtonWriteToUserClickedEventStrategy());
	}
	
	/*
	 * Abstrakcyjna klasa dla mapy strategii do obs³ugi zdarzeñ
	 * @autor Krzyszczak Miko³aj;
	 */
	abstract class ApplicationEventStrategy{
		abstract void execute(final ApplicationEvent applicationEvent);
	}
	
	class ButtonActiveUsersClickedEventStrategy extends ApplicationEventStrategy{

		@Override
		void execute(ApplicationEvent applicationEvent) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class ButtonClearHistoryClickedEventStrategy extends ApplicationEventStrategy{

		@Override
		void execute(ApplicationEvent applicationEvent) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class ButtonFriendUserClickedEventStrategy extends ApplicationEventStrategy{

		@Override
		void execute(ApplicationEvent applicationEvent) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class ButtonLoginClickedEventStrategy extends ApplicationEventStrategy{

		@Override
		void execute(ApplicationEvent applicationEvent) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class ButtonSendMessageEventStrategy extends ApplicationEventStrategy{

		@Override
		void execute(ApplicationEvent applicationEvent) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class ButtonWriteToUserClickedEventStrategy extends ApplicationEventStrategy{

		@Override
		void execute(ApplicationEvent applicationEvent) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
}
