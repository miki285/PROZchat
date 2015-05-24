package modelMessages;
/*
 * Klasa Message do przesy³ania wiadomoœci miêdzy modelem a kontrolerem
 * 
 */
public class DataMessage extends ModelMessages {
	/*
	 * dane przesy³ane w wiadomoœci
	 */
	private Object data;

	/*
	 * konstruktor danego obiektu
	 */
	public DataMessage(final Object data){
		this.data=data;
	}
	
	/*
	 * Metoda zwracaj¹ca wartoœæ data
	 */
	public Object getData(){
		return this.data;
	}
}
