package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;
import java.util.ArrayList;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.model.Message;

/**
 * klasa makiety do wysy�ania rozmowy
 * 
 * @author Miko�aj
 *
 */
public class SendMessageDummy extends SendDummy implements Serializable
{

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	/**
	 * Zmienna na podstawie kt�rej controller u serwera b�dzie decydowa� o
	 * wiadomo�ci
	 */
	private UserId toUserId;
	/** Zawiera tre�� wiadomo�ci */
	private final ArrayList<Message> listOfMessages;

	/** Konstruktor makiety */
	public SendMessageDummy(final ArrayList<Message> listOfMessages)
	{
		this.listOfMessages = listOfMessages;

	}

	/** Metoda ustawiaj�ca toUserId */
	public void setToUserId(UserId userId)
	{
		this.toUserId = userId;
	}

	/** Metoda zwracaj�ca toUserId */

	public UserId getToUserId()
	{
		return toUserId;
	}

	/** Metoda zwracaj�ca tre�� wiadomo�ci */
	public ArrayList<Message> getListOfMessages()
	{
		return this.listOfMessages;
	}

}
