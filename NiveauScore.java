public class NiveauScore extends Niveau{
/*
niveau dont des cubes réapparaissent

*/
  private int posI;
  private int posJ;

  public NiveauScore(int i){
    super(i);
    if (i==4){
      System.out.println("initi");
      posI=0;
      posJ=7;
    }
  }

@Override
  public void reorganisation() {

		while (reste_vide() || reste_col_vide() || animal_sol() ||(tab[posI][posJ] instanceof CaseVide)) {
			if (reste_vide()) gravite();
			if (animal_sol()) supprime_animal();
			if (reste_col_vide())decalage();
			if (animal_sol()) supprime_animal();
      if (tab[posI][posJ] instanceof CaseVide) {
        int rand=(int)(Math.random()*2);
        if (rand==1) tab[posI][posJ]=new Cube(true,"B");
        else tab[posI][posJ]=new Cube(true,"V");
      }
		}
	}





}
