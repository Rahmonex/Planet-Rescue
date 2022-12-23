
import java.awt.Color;

public class Cube extends Case implements  Supprimable, Selectionnable {
	private String couleur;

	public Cube (boolean v,String couleur) {
		super(v);
		this.couleur=couleur;
		switch(couleur) {
		  case "R":
		    c=new Color(224,108, 117);
		    break;
		  case "Y":
			c=new Color(229,192,123);
			break;
		  case "B":
			c=new Color(97,175,239);
			break;
		  case "V":
			  c=new Color(198,120,221);
			  break;
		  case "G":
			  c=new Color(51,143,104);
			  break;
		  default:
		    // code block
		}
	}

	public String toString() {
		return couleur;
	}

	public Color getC() {
		return c;
	}

	public String getCouleur() {
		return couleur;
	}

	public boolean meme_couleur(Cube c) {
		return c.getCouleur().equals(this.couleur);

	}





}
