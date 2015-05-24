package helpfull;



public class UsersDataForClient {
	/*unikalne id klienta*/
	private UserId uniqueID;
	/*zwyk³a nazwa u¿ytkownika*/
	private String userName;
	
	public UsersDataForClient(UserId uniqueId, String name)
	{
		this.uniqueID=uniqueId;
		this.userName=name;
	}
	
	public String getName(){
		return this.userName;
	}

	public UserId getUserId(){
		return this.uniqueID;
	}
}
