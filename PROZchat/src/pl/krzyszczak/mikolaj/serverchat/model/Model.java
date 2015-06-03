package pl.krzyszczak.mikolaj.serverchat.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import pl.krzyszczak.mikolaj.serverchat.exceptions.MessagesNotFoundException;
import pl.krzyszczak.mikolaj.serverchat.exceptions.UserNotFoundException;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.DataMessage;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.InfoMessage;
import pl.krzyszczak.mikolaj.serverchat.modelMessages.ModelMessages;

/**
 * Klasa zawieraj¹ca model serwera chatu
 * 
 * @author Miko³aj
 *
 */

public class Model {
	// Do przechowywania userów
	private HashSet<User> usersSet;
	// format daty
	private SimpleDateFormat sdf;
	private int uniqueId;

	/***********************************************************************************************************************/
	/* KONSTRUKTOR I METODY POTRZEBNE DO UTWORZENIA OBIEKTU KLASY MODEL */

	/***********************************************************************************************************************/
	
	/**
	 * Konstruktor modelu
	 */
	public Model() {
		usersSet = new HashSet<User>();
		sdf = new SimpleDateFormat("HH:mm:ss");
		uniqueId = 1;

	}

	/***********************************************************************************************************************/
	/* METODY DO OBS£UGI LOGUJ¥CYCH SIÊ KLIENTÓW */

	/***********************************************************************************************************************/

	/** Metoda zwracaj¹ca mo¿liw¹ wartoœæ ID klienta */
	public ModelMessages getFreeId() {
		return new DataMessage(uniqueId++);
	}

	/** Metoda sprawdzaj¹ca poprawnoœæ has³a zalogowanego u¿ytkownika */
	public ModelMessages checkUserPassword(UserId userId, UserPassword toCheck) {
		User user = null;
		try {
			user = getUser(userId);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		if (!user.checkPassword(toCheck))
			return new InfoMessage("Has³o niepoprawne");

		return new DataMessage(this.usersSet);
	}

	/**
	 * Metoda dodaj¹ca u¿ytkowników do HashMapy u¿ytkowników na serwerze
	 * 
	 * @param int id, String name
	 */
	public void addUser(UserId id, String name, UserPassword password) {
		usersSet.add(new User(id, name, password));
	}

	/***********************************************************************************************************************/

	/* METODY DO OBS£UGI ODBIERANIA I WYSY£ANIA WIADOMOŒCI */

	/***********************************************************************************************************************/
	
	/**
	 * Metoda dodaj¹ca wiadomoœæ do ka¿dego z u¿ytkowników danej konwersacji
	 * 
	 * @param int idSender, int idTaker, String msg
	 */
	public ModelMessages addUserMessage(UserId idSender, UserId idTaker,
			String msg) {
		User sender;
		User taker;

		try {
			sender = getUser(idSender);
			taker = getUser(idTaker);
			Date date = Calendar.getInstance().getTime();
			Message message = new Message(sender, taker, date, sdf.format(date)
					+ " " + sender.getUserName() + "\n" + msg + "\n");
			sender.addMessage(taker, message);
			taker.addMessage(sender, message);
		} catch (UserNotFoundException e) {
			return new InfoMessage("Nie ma uzytkownikow o takich numerach ID");
		}

		return this.sendMessageHistory(sender, taker);
	}

	/**
	 * Prywatna klasa do wysy³ania wiadomoœci
	 */
	private ModelMessages sendMessageHistory(User sender, User taker) {
		String msg;
		try {
			msg = this.getMessageHistory(sender, taker);
		} catch (MessagesNotFoundException e) {
			return new InfoMessage("Brak wiadomoœci do wyœwietlenia");
		}
		return new DataMessage(msg);
	}

	/**
	 * Prywatna metoda tworz¹ca rozmowê miêdzy dwoma u¿ytkownikami po ich ID
	 * 
	 * @param int idSender, int idTaker
	 */
	private String getMessageHistory(User sender, User taker)
			throws MessagesNotFoundException {
		HashSet<Message> conversation = new HashSet<Message>();
		String finishConversation = new String("");

		conversation.addAll(sender.getMessageHistory(taker));
		if (!conversation.isEmpty()) {
			ArrayList<Message> listOfMessages = new ArrayList<Message>();
			listOfMessages.addAll(conversation);
			Collections.sort(listOfMessages);

			for (Message msg : listOfMessages) {
				finishConversation = finishConversation + msg.getMessage();
			}
		}
		throw new MessagesNotFoundException();
	}

	/***********************************************************************************************************************/

	/* OBS£UGA PRÓŒB O KONTAKTY */

	/***********************************************************************************************************************/

	/**
	 * Metoda dodaj¹ca osobê do znajomych danego klienta
	 * 
	 * @param int idSender, int idTaker
	 */
	public ModelMessages addUserContact(UserId idSender, UserId idTaker) {
		User sender;
		User taker;
		try {
			sender = getUser(idSender);
			taker = getUser(idTaker);
			sender.addContact(taker);
		} catch (UserNotFoundException e) {
			return new InfoMessage("Nie ma uzytkownika o takim numerze ID");
		}
		return this.getUsersContacts(idSender);
	}

	/**
	 * Metoda zwracaj¹ca kontakty danego u¿ytkownika
	 * 
	 * @param int id
	 */
	public ModelMessages getUsersContacts(UserId id) {
		User user;
		try {
			user = getUser(id);
		} catch (UserNotFoundException e) {
			return new InfoMessage("Nie ma uzytkownikow o takich numerach ID");
		}
		return new DataMessage(this.getContacts(user));
	}

	/**
	 * Metoda wysy³aj¹ca wszystkich u¿ytkowników danego chatu
	 */
	public ModelMessages getAllUsers() {
		return new DataMessage(this.getAllUsersSet());
	}

	/**
	 * Metoda prywatna pomagaj¹ca przy zwracaniu informacji o kontaktach
	 * u¿ytkownika
	 */
	private HashSet<UsersDataForClient> getContacts(User user) {
		HashSet<UsersDataForClient> contactsSet = new HashSet<UsersDataForClient>();
		for (User contact : user.getContacts()) {
			contactsSet.add(new UsersDataForClient(contact.getUserId(), contact
					.getUserName()));
		}
		return contactsSet;
	}

	private HashSet<UsersDataForClient> getAllUsersSet() {
		HashSet<UsersDataForClient> allUsersSet = new HashSet<UsersDataForClient>();
		for (User contact : usersSet) {
			allUsersSet.add(new UsersDataForClient(contact.getUserId(), contact
					.getUserName()));
		}
		return allUsersSet;

	}

	/***********************************************************************************************************************/

	/* METODY PRYWATNE KLASY MODELU POMAGAJ¥CE W OBS£UGE M.IN. SETA */

	/***********************************************************************************************************************/
	/**
	 * Klasa zwracaj¹ca obiekt klienta okreœlonego po jego ID
	 */
	private User getUser(UserId id) throws UserNotFoundException {
		for (User user : usersSet) {
			if (id.equals(user.getUserId())) {
				return user;
			}
		}
		throw new UserNotFoundException();
	}

	/*
	 * public void deleteHistory(int idSender, int idTaker){
	 * usersMap.get(idSender).removeHistory(idTaker); }
	 */
}
