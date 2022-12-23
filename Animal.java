
import java.awt.Color;

	public class Animal extends Case implements Supprimable{

	String animal;
	public Animal (boolean b) {
		super(b);
		animal = "A";
		c=Color.orange; 
	}

	public String toString() {
		return animal;
	}


}
