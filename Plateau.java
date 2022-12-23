
import java.awt.Color;


public class Plateau {
	protected final static int DEFAULT_PRICE_ACTION=3000;
	protected int hau;
	protected int lar;
	protected Case [][] tab;
	protected int nb_ani;
	protected int to_be_rescued;
	protected Action action;
	protected int blocs_exploses;
	protected int premLigneVisible;
	protected int derLigneVisible;
	// partie visible ?

	public Plateau () {
		tab = new Case[hau][lar];
		for (int i=0; i<hau;i++) {
			for (int j=0; j<lar;j++) {
				tab[i][j]=null;
			}
		}
		action=null;
		blocs_exploses=0;
	}

	public int getpremL() {
		return premLigneVisible;
	}

	public int getderL() {
		return derLigneVisible;
	}

	public int getPrice() {
		return DEFAULT_PRICE_ACTION;
	}

	public void setFusee(int j) {
		action=new Fusee(j);
	}

	public void setBallon(Color couleur) {

		action=new Ballon(couleur);
	}

	class Action  {
		String nom;
		//Action(String n) {nom=n;}
		void action() {

		}
	}

	class Fusee extends Action {
		int colonne;
		Fusee(int colonne) {
			this.colonne=colonne;
		}
		@Override
		void action () {

			 supprime_colonne(colonne);
		 }
	}

	class Ballon extends Action {
		Color couleur;
		Ballon(Color couleur) {

			this.couleur=couleur;
		}
		@Override
		void action() {

			supprime_couleur(couleur);
		}
	}

	public Action getAction() {
		return action;
	}

	public Case [][] getTab() {
		return tab;
	}

	public int getHau() {
		return hau;
	}

	public int getLar() {
		return lar;
	}
	public int nb_ani() {
		return nb_ani;
	}

	public int reste_a_sauver() {
		return to_be_rescued-nb_ani;
	}

	public int getToBeRescued() {
		return to_be_rescued;
	}

	public void affiche_rescued() {
		System.out.println(reste_a_sauver()+"/"+to_be_rescued+" sauvés");
	}

	public Case getCase(int i, int j) {
		return (tab[i][j]);
	}

	public int recup_score() {
		// la formule de calcul du score dans pet rescue est
		//(nombre de blocs explosés au carré) fois 10
		return (blocs_exploses*blocs_exploses)*10;
	}

	public boolean jeuPerdu() {
		return false;
	}

	public boolean jeuGagne() {
		return nb_ani==0;
	}

	public void afficher() {
		System.out.print("    ");
		for (int j=0; j<lar;j++) System.out.print(" "+j+"  ");
		System.out.println(" ");

		for (int i =0; i<hau;i++) {
			if (tab[i][0].estVisible()) {
			System.out.print(" "+i+" ");
			System.out.print("| ");
			for (int j=0; j<lar;j++) {
				System.out.print(tab[i][j]+ " | ");
			}
			System.out.println("");
			System.out.print("   ");
			for (int j=0; j<lar*4;j++) {

				System.out.print("-");
			}
			System.out.println("");
		}
	}
		System.out.println(" ");
	}

	// partie visible :
	//public void affichage_
	public int premLigneVisible() {
		for (int i=0; i<hau;i++) {
			if (tab[i][0].estVisible()) return i;
		}
		return 0;
	}

	public int derLigneVisible() {
		for (int i=hau-1; i>=0;i--) {
			if (tab[i][0].estVisible()) return i;
		}
		return 0;
	}


	public void invisibiliser() {
		premLigneVisible=premLigneVisible();
		derLigneVisible=derLigneVisible()+1;
		while (premLigneVisible<=(hau-8)) {
			System.out.println("invis");

			for (int j=0; j<lar; j++){
				if ((tab[premLigneVisible][j] instanceof Supprimable)) {
					return;
				}
			}
			/*for (int j=0; j<lar; j++) {
				System.out.println("invis ligne "+premLigneVisible);
				tab[premLigneVisible][j].setInvisible();
			}*/
			tab[premLigneVisible][0].setInvisible();

			premLigneVisible++;

			/*for (int j=0;j<lar; j++) {
				System.out.println("révèle ligne "+derLigneVisible);
				if (!horsLimite(derLigneVisible,j)) tab[derLigneVisible][j].setVisible();
			}*/
			if (!horsLimite(derLigneVisible,0)) tab[derLigneVisible][0].setVisible();
			derLigneVisible++;
		}
	}

	private void supprime_colonne(int j) {
		for (int i=0;i<hau;i++) {

			if ((!horsLimite(i,j) && tab[i][j] instanceof Supprimable )) {
				if(tab[i][j] instanceof Animal) {
					nb_ani--;
				}
				blocs_exploses++;
				tab[i][j]=new CaseVide(true);
			}
		}
	}

	private void supprime_couleur(Color couleur) {
		for (int i=0; i<hau;i++) {
			for(int j=0; j<lar;j++) {
				if  ((!horsLimite(i,j) && tab[i][j] instanceof Supprimable )) {
					if(tab[i][j] instanceof Cube && ((Cube)tab[i][j]).getC().equals(couleur)) {
						blocs_exploses++;
							tab[i][j]=new CaseVide(true);
						}
					}
				}
			}
		}


	public boolean reste_col_vide() {

		for(int i=hau-1;i>=0;i--) {
			for (int j=1;j<lar;j++) {
				if (( !horsLimite(i+1,j) && (tab[i+1][j] instanceof Obstacle || tab[i+1][j] instanceof HorsJeu))) {
					if (!horsLimite(i,j-1) && tab[i][j-1] instanceof CaseVide && tab[i][j] instanceof Supprimable) return true;
				} else {
					if (!horsLimite(i,j-1)  && tab[i][j-1] instanceof CaseVide && tab[i][j] instanceof Supprimable
					&& horsLimite(i+1,j)) return true;
				}
				}
			}
			return false;
		}


	public void decale_colonne(int j) {

		for (int i=hau-1; i>=0;i--) {
			if (tab[i][j] instanceof Supprimable) {
				if (tab[i][j-1] instanceof CaseVide && ((!horsLimite(i+1,j) && (!(tab[i+1][j] instanceof Supprimable))) || horsLimite(i+1,j))) {
					tab[i][j-1]=tab[i][j];
					tab[i][j]=new CaseVide(true);
				}
			}
		}
	}

	public void decalage() {
		int der=hau-1;
		int k=der;
		boolean tmp=true;
		int c=der;
		boolean obstacle=false;
		for (int j=1; j<lar;j++) {
			k=der;
			c=der;
			tmp=true;
			obstacle =false;
			while(!horsLimite(c,j) && c>=0) {
				if ((tab[c][j] instanceof Obstacle || tab[c][j] instanceof HorsJeu) && !horsLimite(c-1,j)) {
					obstacle=true;
				}
				c--;
			}
			while (k>=0 && obstacle==true && !horsLimite(k,j) && (((tab[k][j] instanceof Obstacle || tab[k][j] instanceof HorsJeu || tab[k][j] instanceof CaseVide))|| (tab[k][j] instanceof Supprimable && (!horsLimite(k,j-1) && !(tab[k][j-1] instanceof CaseVide) ) ))){
				k--;
			}
			for (int i=k;i>=0;i--) {
				if (!horsLimite(k,j-1) && (!(tab[k][j-1] instanceof CaseVide)) && tab[k][j] instanceof Supprimable) {
					tmp=false;
				}
			}
			if (!horsLimite(k,j-1) && tab[k][j-1] instanceof CaseVide && (!(tab[k][j] instanceof CaseVide)) && (!(tab[k][j] instanceof Obstacle)) && (!(tab[k][j] instanceof HorsJeu))) {
				if (!reste_vide() && tmp==true) {
					decale_colonne(j);
				}
			}
		}
	}

	public void gravite() {

		for (int i=0;i<tab.length;i++) {
			for (int j=0; j<tab[i].length; j++) {
				if (!(tab[i][j] instanceof CaseVide) && !(tab[i][j] instanceof HorsJeu) && reste_vide(i,j)) {
					comble_vide(i,j);
				}
			}
		}
	}

	private boolean reste_vide(int i, int j) {
		if (horsLimite(i,j)) {
			System.out.println("hors limite");
			return false;
		}
		if (!horsLimite(i+1,j) && tab[i+1][j] instanceof CaseVide) return true;
		return false;
	}

	public boolean reste_vide() {

		for (int i=0; i<tab.length-1;i++) {
			for (int j=0; j<tab[i].length; j++) {
				if (!(tab[i][j] instanceof CaseVide) && (!(tab[i][j] instanceof HorsJeu)) && (!(tab[i][j] instanceof Obstacle)) && tab[i+1][j] instanceof CaseVide) {
					return true;
				}
			}
		}
		return false;
	}

	private void comble_vide(int i, int j) {
		int sol = tab.length-1;
		int tmp = i;
		while(!horsLimite(tmp,j) && tmp<=sol && reste_vide(tmp,j)) {
			tmp++;
		}
			if (tab[i][j] instanceof Supprimable) {
				tab[tmp][j]=tab[i][j];
	      tab[i][j]=new CaseVide(true);
		}
	}

	public boolean animal_sol() {
		int i=tab.length-1;
		for (int j=0; j<tab[i].length; j++) {
			if (tab[i][j] instanceof Animal) {
				return true;
			}
		}
		return false;
	}

	public void supprime_animal() {
		int i=tab.length-1;
		for (int j=0; j<tab[i].length;j++) {
			if (tab[i][j] instanceof Animal) {
				tab[i][j]=new CaseVide(true);
				nb_ani--;
			}
		}
	}

	public void reorganisation() {

		while (reste_vide() || reste_col_vide() || animal_sol()) {
			if (reste_vide()) {
				gravite();
			}
			if (animal_sol()) {
				supprime_animal();
			}
			if (reste_col_vide()){
				decalage();
			}
			if (animal_sol()) supprime_animal();
		}
		//if (hau>8) invisibiliser();
	}
	//===============================================================

	public boolean horsLimite (int x, int y) {
        if (x>=0 && x<hau && y>=0 && y<lar) {
        	return false;
        }
        return true;
    }

	// méthodes pour la suppression ==================================================


	public void suppr(int i, int j) {
		String s=tab[i][j].toString();
		selection(i,j,s);
		suppression();
	}

	private boolean condition(int i, int j, String c) {
		return (!horsLimite(i,j) && ((tab[i][j] instanceof Selectionnable)) &&
				(tab[i][j] instanceof Cube
				&& (!tab[i][j].estSelectionne() &&
						((((Cube)tab[i][j]).getCouleur().equals(c))))
				));
	}


	private boolean direction(int i, int j, String couleur) {
		if (condition(i,j,couleur)) {
		if (!horsLimite(i,j) && tab[i][j] instanceof Selectionnable && tab[i][j] instanceof Cube) {
			if (((Selectionnable)tab[i][j]).meme_couleur((Cube)(tab[i][j]))) {
				return true;
			}
		}
	} return false;
}

	private void selection (int i, int j, String couleur) {
		if ((!(tab[i][j] instanceof Cube))) return;
		if (condition(i,j+1,couleur) ||(condition(i,j-1,couleur)) ||(condition(i+1,j,couleur)) ||(condition(i-1,j,couleur))) {
			tab[i][j].select();
		} else {
			return;
		}
		if (direction(i,j+1,couleur)) {
			tab[i][j+1].select();
			selection ( i, j+1, couleur);
		}
		if (direction(i,j-1,couleur)) {
			tab[i][j-1].select();
			selection ( i, j-1, couleur);
		}
		if (direction(i+1, j,couleur)) {
			tab[i+1][j].select();
			selection ( i+1, j, couleur);
		}
		if (direction(i-1,j,couleur)) {
			tab[i-1][j].select();
			selection ( i-1, j, couleur);
		}
	}

	public void suppression() { 
		for (int i=0; i< hau;i++) {
			for(int j=0; j<lar; j++) {
				if (tab[i][j].estSelectionne()) {
					tab[i][j]=new CaseVide(true);
					blocs_exploses++;
			}
		}
	}
}








}
