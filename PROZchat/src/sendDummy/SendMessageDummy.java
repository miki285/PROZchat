package sendDummy;

import helpfull.UserId;

import java.io.Serializable;

/*
 * Makieta do wysy³ania wiadomoœci
 */
public class SendMessageDummy extends SendDummy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*Zawiera treœæ wiadomoœci*/
	private final String message;
	/*UserId osoby z któr¹ rozmawia klient*/
	private final UserId withWho;
	
	
	/*Konstruktor makiety*/
	public SendMessageDummy(final String msg, final UserId withWho){
		this.message=msg;
		this.withWho=withWho;
	}
	
	
	/*Metoda zwracaj¹ca treœæ wiadomoœci */
	public String getMessage(){
		return this.message;
	}
	
	
	/*Metoda zwracaj¹ca UserId osoby z któr¹ pisze u¿ytkownik*/
	public UserId getUserId(){
		return this.withWho;
	}
	

}
