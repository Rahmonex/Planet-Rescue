
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Niveau extends Plateau  {


	private int id;
	private final String path;
	public Niveau(int id) {
		// on construit le niveau demandé avec le fichier niveaux qu'on parse


		String parsed="";
		this.id = id;
		path="niveaux";
		try {

			parsed=extrait_chaine(id);
			nb_ani=parse_animaux(parsed);
			to_be_rescued=parse_animaux(parsed);
			Case [][] pl=construit(parsed);
			this.hau=pl.length;
			this.lar=pl[0].length;
			tab = new Case[pl.length][pl[0].length];
			for (int i=0; i<hau;i++) {
				for (int j=0; j<lar;j++) {
					tab[i][j]=pl[i][j];
				}
			}
		} catch(IOException e) {
			System.out.println("Erreur lors d’ouverture fichier:");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public int getId() {
		return id;
	}


	private String extrait_chaine (int i) throws IOException {
		String retour="";
		Scanner sc;
		try {
		    sc = new Scanner(new File(path));
		}
		catch(Exception e){
		System.out.println("Erreur lors d’ouverture fichier:");
		e.printStackTrace();
		//System.exit(1);
		return null;
		}
		sc.useDelimiter("\n\\s*\n");
		while (sc.hasNext()) {
			String mot = sc.next();
			Scanner s = new Scanner (mot);
			if (s.next().equals(Integer.toString(i))) {
				return mot;
			}
		}
		System.out.println("exception (implémenter) : niveau n'existe pas");
		return retour;
	}

	private int parse_hauteur(String s) {
		String [] niv=s.split(" ");
		return Integer.parseInt(niv[2]);
	}

	private int parse_largeur(String s) {
		String [] niv=s.split(" ");
		return Integer.parseInt(niv[4]);
	}

	private int parse_animaux(String s) {
		String [] niv=s.split(" ");
		String []tab=niv[niv.length-1].split("\\r?\\n");
		return Integer.parseInt(tab[0]);
	}

	private String[] parse_plateau(String s) {
		// on copie le plateau
		String [] niv=s.split(" ");
		String []tab=niv[niv.length-1].split("\\r?\\n");
		int size = tab.length;
		String [] retour = new String [size-1];
		for (int i=1; i<tab.length;i++) {
			retour[i-1]=tab[i];
		}
		return retour;
	}


	public Case[][] construit (String s) throws IOException {
		Case [][] t = new Case [parse_hauteur(s)][parse_largeur(s)];
		String [] tmp = parse_plateau(s);

		for (int i=0; i<tmp.length; i++) {
			for (int j=0; j<tmp[i].length();j++) {
				if (i>7) {
					switch(tmp[i].charAt(j)) {
					  case '-':
						  t[i][j]=new HorsJeu(false);
						  break;
					  case 'A':
						  t[i][j]=new Animal(false);
						  break;
					  case 'Y':
						  t[i][j]=new Cube(false,"Y");
						  break;
					  case 'R':
						  t[i][j]=new Cube(false,"R");
						  break;
					  case 'B':
						  t[i][j]=new Cube(false,"B");
						  break;
					  case 'V':
						  t[i][j]=new Cube(false,"V");
						  break;
					  case 'G':
						  t[i][j]=new Cube(false,"G");
						  break;
					  case '|':
						  t[i][j]=new Obstacle(false);
						  break;
					  default:
						System.out.print("pas de case");
				}
				}
				else {

				switch(tmp[i].charAt(j)) {
				  case '-':
					  t[i][j]=new HorsJeu(true);
					  break;
				  case 'A':
					  t[i][j]=new Animal(true);
					  break;
				  case 'Y':
					  t[i][j]=new Cube(true,"Y");
					  break;
				  case 'R':
					  t[i][j]=new Cube(true,"R");
					  break;
				  case 'B':
					  t[i][j]=new Cube(true,"B");
					  break;
				  case 'V':
					  t[i][j]=new Cube(true,"V");
					  break;
				  case 'G':
					  t[i][j]=new Cube(true,"G");
					  break;
				  case '|':
					  t[i][j]=new Obstacle(true);
					  break;

				  default:
					System.out.print("pas de case");
				}
				}
			}

		}
		return t;
	}






}
