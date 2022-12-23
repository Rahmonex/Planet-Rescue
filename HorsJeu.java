
import java.awt.Color;

public class HorsJeu extends Case {

	String hors_jeu;
	public HorsJeu (boolean b) {
		super(b);
		hors_jeu="-";
		c=Color.LIGHT_GRAY;

	}

	public String toString() {
		return "-";
	}

}
