
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.Color;


// serialization :
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;


public class Joueur implements Serializable {
  transient protected Niveau niv;
	transient protected int score;
	protected int niv_max;
	protected HashMap<Integer,Integer>scores_par_niveau;
  transient protected Scanner sc;
  protected String nom;


	public Joueur(int i) {
    sc=new Scanner (System.in);
		scores_par_niveau= new HashMap<Integer,Integer>();
		niv=new Niveau(i);
    //niv_max=1;
	}

  public Joueur() {
    sc=new Scanner (System.in);
		scores_par_niveau= new HashMap<Integer,Integer>();
    niv = new Niveau(1);
	}

  public Joueur(String nom) {
    this.nom=nom;
    sc=new Scanner (System.in);
		scores_par_niveau= new HashMap<Integer,Integer>();
    niv = new Niveau(1);
	}

  public HashMap<Integer,Integer> getScores_par_niveau() {
    return scores_par_niveau;
  }

  public void setScores_par_niveau(HashMap<Integer,Integer> hm) {
     scores_par_niveau=hm;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
        this.nom=nom;
    }

  public void jouer() {
    int n=0;

    n=demanderNiveau();
    setNiveau(n);
    niv.afficher();
      while (!gagne() && veutJouerCoup()) {
        if (score>=niv.getPrice()) {
          if (!veutJouer() ) {
            String rep=demanderAction();
  					System.out.println("Action : "+rep);
            setAction(rep);
            if (rep!="") jouerAction();
          } else {
            int[] coord=getCoord();
            System.out.println("coord : ( "+coord[0]+", "+coord[1]+ " )");
            jouerTour(coord[0],coord[1]);
          }
        } else {
          int[] coord=getCoord();
          System.out.println("coord : ( "+coord[0]+", "+coord[1]+ " )");
          jouerTour(coord[0],coord[1]);
        }
        niv.afficher();
        afficheScore();
        niv.affiche_rescued();
      }
      if (gagne()) message_gagne();
      if (veutRejouer()) jouer();
      return;

  }


  public void setAction(String entree) {
		if (entree.equals("F")) {
			int c=demanderColonne();
			niv.setFusee(c);
		} else {
			if (entree.equals("B")) {
				String c=demanderCouleur();
				niv.setBallon(StringToColor(c));
			}
		}
	}

  public Color StringToColor(String s) {
    Color c;
    switch(s) {
		  case "R":
		    c= new Color(224,108, 117);
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
      System.out.println("pas de couleur !!");
		  c=Color.white;
		}
    return c;
  }


  public void jouerTour(int i, int j){
    System.out.println("tour");
    niv.suppr(i,j);
    niv.reorganisation();
    this.setScore();
  }

  public void jouerAction() {
    System.out.println("action");
    niv.getAction().action();
    niv.reorganisation();
    this.setScore();
    score-=niv.getPrice();
}




	public Niveau getNiveau() {
		return niv;
	}

  public void setNiveau(int i) {
    if (i==4) niv=new NiveauScore(i);
    else niv=new Niveau(i);

	}

  public int getNivMax() {
    return niv_max;
  }

  public void setNivMax(int max) {
     niv_max=max;
  }

  public void setScore() {
    score=niv.recup_score();
  }

  public void retireScore() {
    score-=niv.getPrice();
  }


  public int getScore() {
    return score;
  }

  public int getMeilleurScore(int niveau) {
    for (Integer i: scores_par_niveau.keySet()) {
      if (niveau==i){
        return scores_par_niveau.get(i);
      }
    }
    return 0;
  }

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
    this.serialize();
    // serialize informations
    return true;
  }
  return false;
}

public int[] getCoord() {
  int [] tmp=new int[2];
  System.out.println("rentrez deux coord entieres avec un retour a la ligne a chaque fois");
  tmp[0]=sc.nextInt();
  tmp[1]=sc.nextInt();
  return tmp;
}

public boolean veutJouer() {
  int rep;
  do {
      System.out.println("Voulez-vous jouer (0) ou faire une action (1) ?");
      rep = sc.nextInt();
    }while (rep!=0 & rep!=1);
    if (rep==0) return true;
    else return false;
  }

  public boolean veutJouerCoup() {
    int rep;
    do {
        System.out.println("Voulez-vous jouer (0) ou retourner au menu (1)?");
        rep = sc.nextInt();
      }while (rep!=0 & rep!=1);
      if (rep==0) return true;
      else return false;
    }



public boolean veutRejouer() {
  int rep;
  do {
    System.out.println("Voulez-vous retourner au menu? (oui : 0 et quitter :1)");
    rep = sc.nextInt();
  }while (rep!=0 & rep!=1);
  if (rep==0) return true;
  else return false;

}

public String demanderNom() {
  String rep;
  do {
    System.out.println("Quel est votre nom ?");
    rep = sc.nextLine();  // Read user input
  } while (!rep.equals("") & !rep.equals(" "));
  return rep;
  }

public void message_gagne() {
  System.out.println("------------------GAGNÉ-----------------");
  System.out.println("score : "+score+" pts");

}

public String demanderAction() {
      String rep;
      do {
     System.out.println("Entrez F pour fusée et B pour éliminer un cube de couleur ");
       rep = sc.nextLine();  // Read user input
    }while (!rep.equals("F") & !rep.equals("B"));
    return rep;
}

 public int demanderColonne() {
   int rep;
   do {
     System.out.println("Quelle colonne supprimer ?");
     rep = sc.nextInt();
   } while (rep<0 & rep>=niv.getLar());
    return rep;
  }

 public String demanderCouleur() { 
   String rep;
   do {
     System.out.println("Quelle couleur éliminer ?");
     rep = sc.nextLine();
  }while (!rep.equals("R") && !rep.equals("V") && !rep.equals("G") && !rep.equals("Y") && !rep.equals("B"));
     return rep;
  }

  public void afficheScore() {
    System.out.println("score : "+score+ " pts");
  }

  public int demanderNiveau() {
    int rep;

    do {
      System.out.println("Quel niveau jouer ? (Entrez un entier entre 1 et "+(niv_max+1)+")");
         rep = sc.nextInt();
      }while (rep>(niv_max+1));
        return rep;
  }

  public  void serialize() {
		try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(nom);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this);

            out.close();
            file.close();
            System.out.println("Object has been serialized");
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
	}

	public  boolean deserialize() {
		// deserialize si fichier cherché (par le nom utilisateur) existe
		Joueur object1 = null;
		try
       {
				 // Reading the object from a file
				 FileInputStream file = new FileInputStream(nom);
				 ObjectInputStream in = new ObjectInputStream(file);
				 // Method for deserialization of object
				 object1 = (Joueur)in.readObject();
				 in.close();
				 file.close();
				 System.out.println("Object has been deserialized ");
				 System.out.println("nom = " + object1.nom);
				 System.out.println("niveau atteint = " + object1.niv_max);
				 System.out.println("scores par niveau = " + object1.scores_par_niveau);
				 this.nom=(object1.nom);
				 this.niv_max=(object1.niv_max);
				 this.scores_par_niveau=(object1.scores_par_niveau);
       }

       catch(IOException ex)
       {
           System.out.println("IOException is caught");
           return false;
       }

       catch(ClassNotFoundException ex)
       {
           System.out.println("ClassNotFoundException is caught");
           return false;
       }
			 return true;
	}






	// la formule de calcul du score dans pet rescue est
	//(nombre de blocs explosés au carré) fois 10
	//protected abstract void setNom(String demanderNom);

	// méthode choix niveau


}
