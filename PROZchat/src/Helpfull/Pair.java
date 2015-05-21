package Helpfull;

import Model.User;

public class Pair {
	private User user;
	private String name;
	
	public Pair(User user, String name){
		this.user=user;
		this.name=name;
		}
	
	public User first(){
		return this.user;
	}
	
	public String second(){
		return this.name;
	}
	
}
