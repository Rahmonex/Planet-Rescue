
import java.awt.Color;


public class Case {

	protected boolean visible;
	protected boolean selection;
	protected Color c;
	public Case (boolean b) {
		visible=b;
		selection=false;
	}

	public Color getColor() {
		return c;
	}

	public  String toString() {
		return " ";
	}

	public void select() {
		selection=true;
	}

	public boolean estSelectionne() {
		return selection;
	}

	public boolean estVisible() {
		return visible;
	}

	public void setInvisible() {
		visible = false;
	}

	public void setVisible() {
		visible = true;
	}




}
