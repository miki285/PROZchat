package sendDummy;

import helpfull.UserId;

import java.io.Serializable;

/*
 * Makieta do wysy�ania wiadomo�ci
 */
public class SendMessageDummy extends SendDummy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*Zawiera tre�� wiadomo�ci*/
	private final String message;
	/*UserId osoby z kt�r� rozmawia klient*/
	private final UserId withWho;
	
	
	/*Konstruktor makiety*/
	public SendMessageDummy(final String msg, final UserId withWho){
		this.message=msg;
		this.withWho=withWho;
	}
	
	
	/*Metoda zwracaj�ca tre�� wiadomo�ci */
	public String getMessage(){
		return this.message;
	}
	
	
	/*Metoda zwracaj�ca UserId osoby z kt�r� pisze u�ytkownik*/
	public UserId getUserId(){
		return this.withWho;
	}
	

}
