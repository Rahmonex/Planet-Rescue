
import javax.swing.JFrame;

public abstract class Vue extends JFrame {

	protected Joueur model;
	public Vue (Joueur m) {
		model=m;
	}

	public Vue () {
		model=new Joueur();
	}


	protected abstract void message_gagne();


	public abstract void afficheScore();








}
