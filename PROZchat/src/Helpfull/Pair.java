package Helpfull;

public class Pair {
	private int id;
	private String name;
	
	public Pair(int id, String name){
		this.id=id;
		this.name=name;
		}
	
	public int first(){
		return this.id;
	}
	
	public String second(){
		return this.name;
	}
	
}
