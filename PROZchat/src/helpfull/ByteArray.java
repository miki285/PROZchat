package helpfull;


/*klasa przechowuj¹ca zaszyfrowany ci¹g podanego przez u¿ytkownika has³a*/
public class ByteArray {
	/*zmienna przechowuj¹ca ci¹g*/
	private byte[] input;
	
	
	/*konstruktor klasy*/
	public ByteArray(byte[] input){
		this.input=input;
	}
	
	
	/* metoda zwracaj¹ca ci¹g */
	public byte[] getInput(){
		return this.input;
	}
}
