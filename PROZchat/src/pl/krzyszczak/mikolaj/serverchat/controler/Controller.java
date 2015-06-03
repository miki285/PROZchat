package pl.krzyszczak.mikolaj.serverchat.controler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.serverchat.appEvent.*;
import pl.krzyszczak.mikolaj.serverchat.connection.MainServerClass;
import pl.krzyszczak.mikolaj.serverchat.model.Model;
/*
 * Klasa kontrolera
 * @autor Krzyszczak Miko��j
 */
public class Controller {
	//Kolejka blokuj�ca eventy
	private BlockingQueue<ApplicationEvent> eventQueue;
	//Referencja do modelu
    private Model model;
	// referencja do serwera
	private MainServerClass server;
	//Mapa strategii obs�ugi zdarze�
	private Map<Class<? extends ApplicationEvent>, ApplicationEventStrategy> strategyMap;
	
	/*
	 * Konstruktor kontrolera
	 */
	public Controller(final BlockingQueue<ApplicationEvent> eventQueue, final Model model, final MainServerClass server){
		this.eventQueue=eventQueue;
		this.model=model;
		this.server=server;
		
		strategyMap= new HashMap<Class<? extends ApplicationEvent>, Controller.ApplicationEventStrategy>();
		strategyMap.put(ClearHistoryAppEvent.class, new ButtonClearHistoryClickedEventStrategy());
		strategyMap.put(AddFriendAppEvent.class, new ButtonFriendUserClickedEventStrategy());
		strategyMap.put(LoginAppEvent.class, new ButtonLoginClickedEventStrategy());
		strategyMap.put(MessageAppEvent.class, new ButtonSendMessageEventStrategy());
	}
	
	
	/*g��wna metoda kontrolera, czeka on na zdarzenia, a nast�pie je obs�uguje*/
	public void work(){
		while(true)
		{
			try {
				ApplicationEvent applicationEvent= eventQueue.take();
				ApplicationEventStrategy applicationEventStrategy=strategyMap.get(applicationEvent.getClass());
				applicationEventStrategy.execute(applicationEvent);
			} catch (InterruptedException e) {
				//nic nie robimy bo w�tek i tak ma by� zawieszony czekaj�c na zg�oszony Event
			}
			
		}
	}
	
	
	/*
	 * Abstrakcyjna klasa dla mapy strategii do obs�ugi zdarze�
	 * @autor Krzyszczak Miko�aj;
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
