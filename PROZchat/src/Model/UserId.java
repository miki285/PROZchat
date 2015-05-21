package Model;

public class UserId {
	/*
	 * Id użytkownika
	 */
	private int userID;
	
	/*
	 * Konstruktor userid
	 */
	
	public UserId(final int userID){
		this.userID= userID;
	}
	/*
	 * Nadpisana metoda equals
	 * @param Object other
	 */
	public boolean equals (Object other){
		if (other==null) return false;
		if (other==this) return true;
		if (!(other instanceof Integer))return false;
		
		Integer otherId = (int) other;
		return otherId.equals(this.userID);		
	}
	/*
	 * zwraca id użytkownika
	 */
	public int getId(){
		return this.userID;
	}
}
	
