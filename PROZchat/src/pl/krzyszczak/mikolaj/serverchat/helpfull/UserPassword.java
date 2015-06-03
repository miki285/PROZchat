package pl.krzyszczak.mikolaj.serverchat.helpfull;

public class UserPassword
{
	/*
	 * Wartoœæ skrótu has³a u¿ytkownika
	 */
	private int password;
	
	
	/*
	 * konstruktor klasy userPassword
	 */
	public UserPassword(int password){
		this.password=password;
	}
	
	
	/*
	 * Nadpisana metoda equals
	 * @param Object other
	 */
	public boolean equals (Object other){
		if (other==null) return false;
		if (other==this) return true;
		if (!(other instanceof Integer)) return false;
		
		Integer otherId = (int) other;
		return otherId.equals(this.password);		
	}
	
	public int getUserPassword()
	{
		return this.password;
	}
}
