
public class Bot extends Joueur {


	public Bot(int i) {
		super(i);
	}



	public int[] hasard() {
		int i;
		int j;
		do {
			i=(int)(Math.random()*niv.getHau());
			j=(int)(Math.random()*niv.getLar());
		} while (!(niv.getCase(i,j) instanceof Selectionnable));
		//System.out.println("i:"+i);
		//System.out.println("j:"+j);
		int [] retour = {i,j};
		return retour;
	}

@Override
	public void jouerTour(int i, int j){
		int[] coord=hasard();
		i=coord[0];
		j=coord[1];
		System.out.println("i:"+i+" j:"+j);
    niv.suppr(i,j);
    niv.reorganisation();
    this.setScore();
  }

@Override
	public boolean gagne() {
    if (niv.jeuGagne()) {
      if (niv_max<niv.getId()) {
        niv_max = niv.getId();
        scores_par_niveau.put(niv.getId(),score);
    } else {
      if (scores_par_niveau.containsKey(niv.getId()) && score>scores_par_niveau.get(niv.getId())) {
        scores_par_niveau.replace(niv.getId(),score);
      }
    }
    return true;
  }
  return false;
}

@Override
public boolean veutJouer() {
  int rep;
  do {
      System.out.println("Voulez-vous faire jouer le bot (0) ou retourner au menu (1)?");
      rep = sc.nextInt();
    }while (rep!=0 & rep!=1);
    if (rep==0) return true;
    else return false;
  }

@Override
public void jouer() {
	System.out.println("---------------bot--------------------");
	int n=0;
	n=demanderNiveau();
	setNiveau(n);
	niv.afficher();

		while (!gagne() && veutJouer()) {
				jouerTour(0,0);
				niv.afficher();
				afficheScore();
				niv.affiche_rescued();
		}
		if (gagne()) message_gagne();

		if (veutRejouer()) jouer();
		return;
}






}
