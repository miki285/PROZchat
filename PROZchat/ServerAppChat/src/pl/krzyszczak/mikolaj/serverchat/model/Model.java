package pl.krzyszczak.mikolaj.serverchat.model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import pl.krzyszczak.mikolaj.serverchat.appEvent.MessageAppEvent;
import pl.krzyszczak.mikolaj.serverchat.exceptions.UserNotFoundException;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.DataMessage;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.InfoMessage;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.ModelMessages;

/**
 * Klasa zawieraj�ca model serwera chatu
 * 
 * @author Miko�aj
 *
 */

public class Model
{
	/** Set przechowyj�cy wszystkich u�ytkownik�w serwera */
	private HashSet<User> usersSet;
	/** Zmienna pomagaj�ca nadawa� unikalne ID u�ytkownikom*/
	private int uniqueId;
	
	/**
	 * Set przechowuj�cy UserId u�ytkownik�w, kt�rzy pr�bowali si� zalogowa� ale
	 * podali z�e has�o
	 */
	private HashSet<UserId> blockedUsers;

	/***********************************************************************************************************************/
	/* KONSTRUKTOR I METODY POTRZEBNE DO UTWORZENIA OBIEKTU KLASY MODEL */

	/***********************************************************************************************************************/

	/**
	 * Konstruktor modelu
	 * TODO poprawi� to sdf
	 */
	public Model()
	{
		uniqueId=1;
		usersSet = new HashSet<User>();
		//sdf = new SimpleDateFormat("HH:mm:ss");
		blockedUsers = new HashSet<UserId>();

	}

	/***********************************************************************************************************************/
	/* METODY DO OBS�UGI LOGUJ�CYCH SI� KLIENT�W */

	/***********************************************************************************************************************/

	/**Metoda zwracaj�ca unikalne ID dla ka�dego u�ytkownika */
	public UserId setUniqueId()
	{
		return new UserId(uniqueId++);
	}
	
	/** Metoda sprawdzaj�ca poprawno�� has�a zalogowanego u�ytkownika */
	public ModelMessages checkUserPassword(UserId userId, UserPassword toCheck)
	{
		User user = null;
		try
		{
			user = getUser(userId);
		} catch (UserNotFoundException e)
		{
			return new InfoMessage("Nie znaleziono u�ytkownika o takim ID");
		}
		if (!user.checkPassword(toCheck))
		{
			blockedUsers.add(user.getUserId());
			return new InfoMessage("Has�o niepoprawne");
		}
		if(blockedUsers.contains(userId))
			blockedUsers.remove(userId);
		return new DataMessage(this.getContacts(user));
	}

	/**
	 * Metoda sprawdzaj�ca czy dany u�ytkownik kt�ry pr�buje sie zalogowa�,
	 * poda� wcze�niej z�e has�o je�eli tak to umo�liwi mi to zablokowanie
	 * wszystkich funkcjonalno�ci zwi�zanych z danym kontem
	 * 
	 * @param userId
	 * @return
	 */
	public boolean ifUserIsBlocked(UserId userId)
	{
		return blockedUsers.contains(userId);
	
	}

	/**
	 * Metoda dodaj�ca u�ytkownik�w do HashMapy u�ytkownik�w na serwerze
	 * 
	 * @param int id, String name
	 */
	public void addUser(UserId userId, String name, UserPassword password)
	{
		usersSet.add(new User(userId, name, password));
	}

	/***********************************************************************************************************************/

	/* METODY DO OBS�UGI ODBIERANIA I WYSY�ANIA WIADOMO�CI */

	/***********************************************************************************************************************/

	/**
	 * Metoda dodaj�ca wiadomo�� do ka�dego z u�ytkownik�w danej konwersacji
	 * 
	 * @param int byUserId, int toUserId, String msg
	 */
	public ModelMessages addUserMessage(MessageAppEvent messageAppEvent)
	{
	
		UserId byUserId=messageAppEvent.getByUser();
		UserId toUserId= messageAppEvent.getToUser();
		String msg= messageAppEvent.getMsg();
		if(ifUserIsBlocked(byUserId))
			return new InfoMessage("Najpierw sie zaloguj!");
		User byUser;
		User toUser;

		try
		{
			byUser = getUser(byUserId);
			toUser = getUser(toUserId);
			Date date = Calendar.getInstance().getTime();
			Message message = new Message(byUserId, toUserId, date, msg + "\n");
			byUser.addMessage(toUser, message);
			toUser.addMessage(byUser, message);
		} catch (UserNotFoundException e)
		{
			return new InfoMessage("Nie ma uzytkownikow o takich numerach ID");
		}

		return this.getMessageHistory(byUser, toUser);
	}



	/**
	 * Prywatna metoda tworz�ca rozmow� mi�dzy dwoma u�ytkownikami po ich ID
	 */
	private ModelMessages getMessageHistory(User byUser, User toUser)
	{
		HashSet<Message> conversation = new HashSet<Message>();

		conversation.addAll(byUser.getMessageHistory(toUser));
		if (!conversation.isEmpty())
		{
			ArrayList<Message> listOfMessages = new ArrayList<Message>();
			listOfMessages.addAll(conversation);
			Collections.sort(listOfMessages);

			return  new DataMessage(listOfMessages);
		}
		return new InfoMessage("Brak wiadomo�ci do wy�wietlenia");
		
	}

	/***********************************************************************************************************************/

	/* OBS�UGA PRӌB O KONTAKTY */

	/***********************************************************************************************************************/

	/**
	 * Metoda dodaj�ca osob� do znajomych danego klienta
	 * 
	 * @param int byUserId, int toUserId
	 */
	public ModelMessages addUserContact(UserId byUserId, UserId idWhichUser)
	{
		if(ifUserIsBlocked(byUserId))
			return new InfoMessage("Najpierw sie zaloguj!");
		User byUser;
		User toUser;
		try
		{
			byUser = getUser(byUserId);
			toUser = getUser(idWhichUser);
			byUser.addContact(toUser);
		} catch (UserNotFoundException e)
		{
			return new InfoMessage("Nie ma uzytkownika o takim numerze ID");
		}
		return this.getUsersContacts(byUserId);
	}

	/**
	 * Metoda zwracaj�ca kontakty danego u�ytkownika
	 * 
	 * @param int id
	 */
	private ModelMessages getUsersContacts(UserId userId)
	{
		
		User user;
		try
		{
			user = getUser(userId);
		} catch (UserNotFoundException e)
		{
			return new InfoMessage("Nie ma uzytkownika o takim numerze ID");
		}
		return new DataMessage(this.getContacts(user));
	}

	/**
	 * Metoda prywatna pomagaj�ca przy zwracaniu informacji o kontaktach
	 * u�ytkownika
	 */
	private HashSet<UsersDataForClient> getContacts(User user)
	{
		HashSet<UsersDataForClient> contactsSet = new HashSet<UsersDataForClient>();
		for (User contact : user.getContacts())
		{
			contactsSet.add(new UsersDataForClient(contact.getUserId(), contact
					.getUserName()));
		}
		return contactsSet;
	}

	/**
	 * Metoda zwracaj�ca HashSet wszystkich klient�w zalogowanych na serwerze
	 * 
	 * @return
	 */
	public HashSet<UsersDataForClient> getAllUsersSet()
	{
		
		HashSet<UsersDataForClient> allUsersSet = new HashSet<UsersDataForClient>();
		for (User contact : usersSet)
		{
			allUsersSet.add(new UsersDataForClient(contact.getUserId(), contact
					.getUserName()));
		}
		return allUsersSet;

	}

	/***********************************************************************************************************************/

	/* METODY PRYWATNE KLASY MODELU POMAGAJ�CE W OBS�UGE M.IN. SETA */

	/***********************************************************************************************************************/
	/**
	 * metoda zwracaj�ca obiekt klienta okre�lonego po jego ID
	 */
	private User getUser(UserId userId) throws UserNotFoundException
	{
		for (User user : usersSet)
		{
			if (userId.equals(user.getUserId()))
			{
				return user;
			}
		}
		throw new UserNotFoundException();
	}

	/**Metoda zwracaj�ca obiektu UserId*/
	public UserId getUserId(UserId userId)
	{
		UserId getUserId = null;
		try
		{
			getUserId= getUser(userId).getUserId();
		} catch (UserNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getUserId;
	}

}
