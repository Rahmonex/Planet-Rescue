
import java.awt.Color;

public class Obstacle  extends Case{

    private String obstacle;

	public Obstacle (boolean v) {
		super(v);
		obstacle= "[";
		c=Color.black;
	}

	public String toString() {
		return obstacle;
	}

}
