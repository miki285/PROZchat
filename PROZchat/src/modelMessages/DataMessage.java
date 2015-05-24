package modelMessages;
/*
 * Klasa Message do przesy�ania wiadomo�ci mi�dzy modelem a kontrolerem
 * 
 */
public class DataMessage extends ModelMessages {
	/*
	 * dane przesy�ane w wiadomo�ci
	 */
	private Object data;

	/*
	 * konstruktor danego obiektu
	 */
	public DataMessage(final Object data){
		this.data=data;
	}
	
	/*
	 * Metoda zwracaj�ca warto�� data
	 */
	public Object getData(){
		return this.data;
	}
}
