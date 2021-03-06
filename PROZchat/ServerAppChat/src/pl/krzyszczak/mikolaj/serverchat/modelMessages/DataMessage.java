package pl.krzyszczak.mikolaj.serverchat.modelMessages;
/*
 * Klasa Message do przesyłania wiadomości między modelem a kontrolerem
 * 
 */
public class DataMessage extends ModelMessages {
	/*
	 * dane przesyłane w wiadomości
	 */
	private Object data;

	/*
	 * konstruktor danego obiektu
	 */
	public DataMessage(final Object data){
		this.data=data;
	}
	
	/*
	 * Metoda zwracająca wartość data
	 */
	public Object getData(){
		return this.data;
	}
}
