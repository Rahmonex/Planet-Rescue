
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;



public class Partie {

	public static String accueil() {
		System.out.println("Bienvenue.");
		System.out.println("Quel est votre nom ?");
		Scanner sc=new Scanner (System.in);
		String rep=sc.nextLine();
		return rep;
	}

	public static boolean InterfaceGraphique() {
		Scanner sc=new Scanner (System.in);
		int rep;
		do {
			System.out.println("Voulez-vous jouer en Interface Graphique ? (oui : 0 et non :1)");
			rep = sc.nextInt();

		}while (rep!=0 & rep!=1);
		if (rep==0) return true;
		else return false;

	}

	public static boolean modeBot() {
		Scanner sc=new Scanner (System.in);
		int rep;
		do {
			System.out.println("Voulez-vous faire jouer un Bot ? (oui : 0 et non :1)");
			rep = sc.nextInt();
			if (rep==0) return true;
			else return false;
		}
		while (rep!=0 || rep!=1);
	}


	public static void main (String [] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		Joueur j;
		if (modeBot()) {
			j=new Bot(1);
		} else {
			String nom=accueil();
			j = new Joueur(nom);

			if (!j.deserialize()) {
				j.setNom(nom);
				j.serialize();
			}
		}

		/*if (InterfaceGraphique()) {
			javax.swing.SwingUtilities.invokeLater( () ->
			{
			new Launcher(j);
		});*/

		if (InterfaceGraphique()) {
			javax.swing.SwingUtilities.invokeLater( () ->
			{
				try {
					Interface.lancerInterface(j);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			});
	} else {

		System.out.println("niv max :"+j.getNivMax());
		j.jouer();
	}
	}
}