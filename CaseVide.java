
import java.awt.Color;

public class CaseVide extends Case {

	private final String vide=" ";

	public CaseVide(boolean v) {
		super(v);
		c=Color.white;
	}

	public String toString() {
		return vide;
	}

}
