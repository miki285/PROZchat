package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

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
	/** Zawiera tre�� wiadomo�ci */
	private final String message;
	/** UserId osoby z kt�r� rozmawia klient */
	private final UserId withWho;
	/** UserId osoby kt�ra napisa�a wiadomo�� */
	private final UserId byWho;

	/** Konstruktor makiety */
	public SendMessageDummy(final String msg, final UserId withWho,
			final UserId byWho)
	{
		this.message = msg;
		this.withWho = withWho;
		this.byWho = byWho;
	}

	/** Metoda zwracaj�ca tre�� wiadomo�ci */
	public String getMessage()
	{
		return this.message;
	}

	/** Metoda zwracaj�ca UserId osoby z kt�r� pisze u�ytkownik */
	public UserId getWithUserId()
	{
		return this.withWho;
	}

	/** Metoda zwracaj�ca UserId osoby kt�ra napisa�a wiadomo�� */
	public UserId getByUserId()
	{
		return this.byWho;
	}

}
