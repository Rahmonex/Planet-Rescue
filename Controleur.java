import java.awt.Color;


public class Controleur {

	private Joueur m;
	private Cadre v;

	public Controleur(Joueur x) {
		m=x;
	}

	public void setView(Cadre v) {
		this.v=v;

		v.addListenerAccueil();
	}

	public void appui(int i, int j) {
		System.out.println("tour : i : "+ i+ " j : "+j);
		m.jouerTour(i,j);
		v.miseAJour();
	}

	public void joue_bot() {

		m.jouerTour(0,0);
		v.miseAJour();

	}

	public void fusee(int j) {
		m.getNiveau().setFusee(j);
		m.jouerAction();
		m.retireScore();
		v.miseAJour();
	}

	public void ballon (Color c) {
		m.getNiveau().setBallon(c);
		m.jouerAction();
		m.retireScore();
		v.miseAJour();
	}



	public boolean jeuGagne() {
		if (m.getNiveau().nb_ani()==0) {
			m.gagne();
			v.message_gagne();
			return true;
		}
		return false;
	}









}
