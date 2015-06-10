package pl.krzyszczak.mikolaj.serverchat.sendDummy;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

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
	/** Zawiera treœæ wiadomoœci */
	private final String message;
	/** UserId osoby z któr¹ rozmawia klient */
	private final UserId withWho;
	/** UserId osoby która napisa³a wiadomoœæ */
	private final UserId byWho;

	/** Konstruktor makiety */
	public SendMessageDummy(final String msg, final UserId withWho,
			final UserId byWho)
	{
		this.message = msg;
		this.withWho = withWho;
		this.byWho = byWho;
	}

	/** Metoda zwracaj¹ca treœæ wiadomoœci */
	public String getMessage()
	{
		return this.message;
	}

	/** Metoda zwracaj¹ca UserId osoby z któr¹ pisze u¿ytkownik */
	public UserId getWithUserId()
	{
		return this.withWho;
	}

	/** Metoda zwracaj¹ca UserId osoby która napisa³a wiadomoœæ */
	public UserId getByUserId()
	{
		return this.byWho;
	}

}
