package helpfull;


/*klasa przechowuj�ca zaszyfrowany ci�g podanego przez u�ytkownika has�a*/
public class ByteArray {
	/*zmienna przechowuj�ca ci�g*/
	private byte[] input;
	
	
	/*konstruktor klasy*/
	public ByteArray(byte[] input){
		this.input=input;
	}
	
	
	/* metoda zwracaj�ca ci�g */
	public byte[] getInput(){
		return this.input;
	}
}
