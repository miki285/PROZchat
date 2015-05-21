package controler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import AplicationEvent.*;
import Connection.Server;
import Model.Model;
/*
 * Klasa kontrolera
 * @autor Krzyszczak Miko³¹j
 */
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
		strategyMap.put(ButtonClearHistoryEvent.class, new ButtonClearHistoryClickedEventStrategy());
		strategyMap.put(ButtonFriendUserEvent.class, new ButtonFriendUserClickedEventStrategy());
		strategyMap.put(ButtonLoginClickedEvent.class, new ButtonLoginClickedEventStrategy());
		strategyMap.put(ButtonSendMessageEvent.class, new ButtonSendMessageEventStrategy());
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
