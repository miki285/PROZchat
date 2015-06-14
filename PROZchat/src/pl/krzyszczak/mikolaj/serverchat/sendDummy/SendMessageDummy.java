package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;
import java.util.ArrayList;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.model.Message;

/**
 * klasa makiety do wysy³ania rozmowy
 * 
 * @author Miko³aj
 *
 */
public class SendMessageDummy extends SendDummy implements Serializable
{

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	/**
	 * Zmienna na podstawie której controller u serwera bêdzie decydowa³ o
	 * wiadomoœci
	 */
	private UserId toUserId;
	/** Zawiera treœæ wiadomoœci */
	private final ArrayList<Message> listOfMessages;

	/** Konstruktor makiety */
	public SendMessageDummy(final ArrayList<Message> listOfMessages)
	{
		this.listOfMessages = listOfMessages;

	}

	/** Metoda ustawiaj¹ca toUserId */
	public void setToUserId(UserId userId)
	{
		this.toUserId = userId;
	}

	/** Metoda zwracaj¹ca toUserId */

	public UserId getToUserId()
	{
		return toUserId;
	}

	/** Metoda zwracaj¹ca treœæ wiadomoœci */
	public ArrayList<Message> getListOfMessages()
	{
		return this.listOfMessages;
	}

}
