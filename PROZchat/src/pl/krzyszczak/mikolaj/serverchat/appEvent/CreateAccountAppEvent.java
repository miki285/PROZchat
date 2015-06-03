package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;

/**
 * Klasa eventu tworz¹cego nowe konto
 * 
 * @author Miko³aj
 *
 */
public class CreateAccountAppEvent extends ApplicationEvent implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private UserId id;
	private String name;
	private UserPassword password;

	/**
	 * Konstruktor klasy eventu utwórz okno
	 * @param id
	 * @param name
	 * @param password
	 */
	public CreateAccountAppEvent(UserId id, String name, UserPassword password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public UserId getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public UserPassword getPassword() {
		return this.password;
	}

}
