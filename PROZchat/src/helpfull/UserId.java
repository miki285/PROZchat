package helpfull;


public class UserId {
	
	
	/*
	 * Id u¿ytkownika
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
	 * zwraca id u¿ytkownika
	 */
	public int getId(){
		return this.userID;
	}
}
	
