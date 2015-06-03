package pl.krzyszczak.mikolaj.serverchat.appEvent;

import java.io.Serializable;

import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * Klasa eventu dodania przyjaciela daneog klienta
 * 
 * @author Miko³aj FIXME
 */
public class AddFriendAppEvent extends ApplicationEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserId byUser;
	private UserId whichUser;

	public AddFriendAppEvent(UserId byUser, UserId whichUser) {
		this.byUser = byUser;
		this.whichUser = whichUser;
	}

	public UserId getByUser() {
		return this.byUser;
	}

	public UserId getWhichUser() {
		return this.whichUser;
	}
}
