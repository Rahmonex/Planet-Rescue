import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import javax.swing.event.MouseInputListener;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.*;

public class Cadre extends Vue { // étendre vue
	private final static int DEFAULT_SCREENSIZE_LENGTH=800;
	private final static int DEFAULT_SCREENSIZE_WIDTH=620;

	private JPanel accueil;
	private JButton graphique;
	private JButton shell;


	private JPanel menu;
	private JLabel background;
  private JButton returnHome;
  private JButton n1,n2,n3,n4,n5;
  private JLabel fond, fondVide;

	//----------------------------------------------------------------------

	private final static int DEFAULT_CARRE_SIZE=70;
	private JPanel conteneurPrincipal;
	private Controleur control;
	private JLabel score;
	private JLabel meilleurScore;
	private JLabel rescued;
	private JButton ballon;
	private JButton fusee;
	private boolean action_fusee;
	private boolean action_ballon;
	private JLabel ordre_fusee;
	private JLabel ordre_ballon;
	private JButton retour;
	private JButton bot;
	private boolean modeBot;



	private class CarreView extends JPanel implements MouseInputListener{
		int xClick, yClick;
		//Case c;
		CarreView (Case c, int i, int j) {
			int x,y;
			x=(i*75);
			y=(j*75);
			this.setBounds(x, y, DEFAULT_CARRE_SIZE, DEFAULT_CARRE_SIZE);
			this.setBackground(c.getColor());
			this.setBorder(BorderFactory.createLineBorder(Color.white));

		}

		public void setCarreView(Case c, int i, int j) {
			int x,y;
			x=(i*75);
			y=(j*75);
			this.setBounds(x, y, DEFAULT_CARRE_SIZE, DEFAULT_CARRE_SIZE);
			this.setBackground(c.getColor());
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!modeBot) {
				xClick=this.getX();
				yClick=this.getY();
				if (action_fusee) {
					control.fusee(xClick/75);
					action_fusee=false;
					ordre_fusee.setVisible(false);
					return;
				}
				if (action_ballon) {
					control.ballon(this.getBackground());
					action_ballon=false;
					ordre_ballon.setVisible(false);
					return;
				}
				control.appui(yClick/75,xClick/75);
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if (modeBot) return;
 			Color c = this.getBackground();
			if (c.equals(Color.white)) return;
			this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
		@Override
		public void mouseExited(MouseEvent e) {
			if (modeBot) return;
			this.setBorder(BorderFactory.createLineBorder(Color.white));
	}
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}

		public Cadre(Joueur j) {
			super(j);
			if (j instanceof Bot) {
				System.out.println("Vue : mode bot");
				modeBot=true;
			}
			this.setTitle("Planet Rescue Saga");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(true);
			this.setSize(1000,800);
	    this.setResizable(false);
			ImageIcon logo = new ImageIcon("Images/Logo2.png");

	    this.setIconImage(logo.getImage());
			accueil=new JPanel();
	    graphique = new JButton("Interface Graphique");
	    graphique.setBounds(700,400,300,100);
	    graphique.setFont(new Font("Trebuchet",Font.BOLD,50));
	    graphique.setBackground(Color.black);

	    //Shell
	    shell = new JButton("Shell");
	    shell.setBounds(700,700,300,100);
	    shell.setFont(new Font("Trebuchet",Font.BOLD,50));
	    shell.setBackground(Color.black);

			accueil.add(graphique);
			graphique.setVisible(true);
			accueil.add(shell);
			shell.setVisible(true);


			ImageIcon returnBack = new ImageIcon("Images/return.png");
	    ImageIcon img_background = new ImageIcon("Images/gif-bg.gif");
	    background = new JLabel();
	    background.setText("");
	    background.setIcon(img_background);
	    background.setHorizontalTextPosition(JLabel.CENTER);
	    background.setVerticalTextPosition(JLabel.CENTER);
	    background.setBounds(0,0,1000,800);

			accueil.add(background);
			background.setVisible(false);
			this.getContentPane().add(accueil);
			this.pack();

			menu= new JPanel();


	    //Return Home
	    returnHome = new JButton("");
	    returnHome.setIcon(returnBack);
	    returnHome.setBounds(950,700,40,40);
	    returnHome.setBackground(new Color(0,124,101));
	    returnHome.setBorder(BorderFactory.createCompoundBorder());

	    //Niveau 1
	    n1 = new JButton("Niveau 1");
	    n1.setBounds(175,90,200,200);
	    n1.setFont(new Font("Trebuchet",Font.BOLD,25));
	    n1.setForeground(new Color(176,255,134));
	    n1.setBackground(new Color(0,124,101));
	    n1.setBorder(BorderFactory.createCompoundBorder());
	    //Niveau 2
	    n2 = new JButton("Niveau 2");
	    n2.setBounds(405,90,200,200);
	    n2.setFont(new Font("Trebuchet",Font.BOLD,25));
	    n2.setForeground(new Color(176,255,134));
	    n2.setBackground(new Color(0,124,101));
	    n2.setBorder(BorderFactory.createCompoundBorder());
	    //Niveau 3
	    n3 = new JButton("Niveau 3");
	    n3.setBounds(635,90,200,200);
	    n3.setFont(new Font("Trebuchet",Font.BOLD,25));
	    n3.setForeground(new Color(176,255,134));
	    n3.setBackground(new Color(0,124,101));
	    n3.setBorder(BorderFactory.createCompoundBorder());
	    //Niveau 4
	    n4 = new JButton("Niveau 4");
	    n4.setBounds(175,320,200,200);
	    n4.setFont(new Font("Trebuchet",Font.BOLD,25));
	    n4.setForeground(new Color(176,255,134));
	    n4.setBackground(new Color(0,124,101));
	    n4.setBorder(BorderFactory.createCompoundBorder());
	    //Niveau 5
	    n5 = new JButton("Niveau 5");
	    n5.setBounds(405,320,200,200);
	    n5.setFont(new Font("Trebuchet",Font.BOLD,25));
	    n5.setForeground(new Color(176,255,134));
	    n5.setBackground(new Color(0,124,101));
	    n5.setBorder(BorderFactory.createCompoundBorder());



	    menu.add(returnHome);
	    returnHome.setVisible(false);
			menu.add(n1);
	    n1.setVisible(true);
	    menu.add(n2);
	    n2.setVisible(true);
	    menu.add(n3);
	    n3.setVisible(true);
	    menu.add(n4);
	    n4.setVisible(true);
	    menu.add(n5);
	    n5.setVisible(true);



			    ImageIcon img_fondLevel = new ImageIcon("Images/fondLevel.jpg");
			    // ----------------Labels
			    fond = new JLabel();
			    fond.setText("");
			    fond.setIcon(img_fondLevel);
			    //-----
			    fond.setHorizontalTextPosition(JLabel.CENTER);
			    fond.setVerticalTextPosition(JLabel.CENTER);
			    fond.setBounds(0,0,1000,800);


			    //-----------------Main Features
			    menu.add(fond);
			    fond.setVisible(true);


			conteneurPrincipal = new JPanel();

			control=new Controleur(model);
			control.setView(this);

			ImageIcon img_fondVide = new ImageIcon("Images/fond.jpg");
			fondVide = new JLabel();
			fondVide.setText("");
			fondVide.setIcon(img_fondVide);
			//-----
			fondVide.setHorizontalTextPosition(JLabel.CENTER);
			fondVide.setVerticalTextPosition(JLabel.CENTER);
			fondVide.setBounds(0,0,1000,800);

			score=new JLabel("Score : "+model.getScore());
			score.setForeground(Color.black);
			score.setBounds(620,10,200,70);
			conteneurPrincipal.add(score);
			score.setVisible(true);

			meilleurScore=new JLabel("Meilleur score : "+model.getMeilleurScore(model.getNiveau().getId()));
			meilleurScore.setForeground(Color.black);
			meilleurScore.setBounds(620,50,200,70);
			conteneurPrincipal.add(meilleurScore);
			meilleurScore.setVisible(true);

			rescued=new JLabel("rescued : "+model.getNiveau().reste_a_sauver()+"/"+model.getNiveau().getToBeRescued());
			rescued.setForeground(Color.black);
			rescued.setBounds(620,90,200,70);
			conteneurPrincipal.add(rescued);
			rescued.setVisible(true);

			ballon=new JButton("Ballon");
			fusee=new JButton("Fusee");
			bot =new JButton("Bot");
			ballon.setForeground(Color.red);
			fusee.setForeground(Color.red);
			bot.setForeground(Color.blue);

			ordre_fusee=new JLabel("choisissez une colonne ! ");
			ordre_fusee.setForeground(Color.black);
			ordre_fusee.setBounds(620,300,200,70);
			conteneurPrincipal.add(ordre_fusee);
			ordre_fusee.setVisible(false);

			ordre_ballon=new JLabel("choisissez une couleur ! ");
			ordre_ballon.setForeground(Color.black);
			ordre_ballon.setBounds(620,300,200,70);
			conteneurPrincipal.add(ordre_ballon);
			ordre_ballon.setVisible(false);

			retour=new JButton("retour");
			retour.setVisible(true);
			retour.setBounds(620,260,70,50);
			conteneurPrincipal.add(retour);

			ballon.setVisible(true);
			fusee.setVisible(true);
			bot.setVisible(false);
			ballon.setBounds(620,150,70,50);
			fusee.setBounds(620,205,70,50);
			bot.setBounds(620,320,70,50);

			conteneurPrincipal.add(ballon);
			conteneurPrincipal.add(fusee);
			conteneurPrincipal.add(bot);

			conteneurPrincipal.setVisible(false);

		}



		public void btn_niveau(int n) {
			if (modeBot) bot.setVisible(true);
			model.setNiveau(n);
			model.setScore();

			conteneurPrincipal.setLayout(null);
			conteneurPrincipal.setBackground(Color.LIGHT_GRAY);
			setContentPane(conteneurPrincipal);

			revalidate();
			this.addListenerGame();

			for(int i=0;i<model.getNiveau().getHau();i++) {
				for (int j=0;j<model.getNiveau().getLar();j++) {
					if (model.getNiveau().getCase(i,j).estVisible()) {
					CarreView x=new CarreView(model.getNiveau().getCase(i,j),j,i);
					conteneurPrincipal.add(x);
					x.addMouseListener(x);
					this.addMouseMotionListener(x);
				}
				}
			}
			conteneurPrincipal.setVisible(true);
			//------------------------------
			conteneurPrincipal.add(fondVide);
			fond.setVisible(true);
			//---------------------------------
			afficheScore();
	  }

		public void addAllListener() {
			addListenerMenu();
			addListenerGame();
		}

		public void addListenerAccueil() {
			graphique.addActionListener(e ->{
				this.setSize(1000,800);
				menu.setLayout(null);
				menu.setBackground(Color.LIGHT_GRAY);
				setContentPane(menu);
				revalidate();
				this.addListenerMenu();
			});

			shell.addActionListener(e -> {
				this.setVisible(false); //you can't see me!
		    dispose();
				model.jouer();
			});
		}

		private void active_boutton(JButton b,int i) {
			if (model.getNivMax() >=(i-1)) {
				b.addActionListener(e -> {
	      btn_niveau(i);
	    });
		} else {
			b.addActionListener(e -> {
		});
		}
	}

		public void addListenerMenu() {

			returnHome.addActionListener(e -> {
	    });

		 active_boutton(n1,1);
		 active_boutton(n2,2);
		 active_boutton(n3,3);
		 active_boutton(n4,4);
		 active_boutton(n5,5);
		}

		public void addListenerActions() {
			if (modeBot) return;
			if (model.getScore()>=model.getNiveau().getPrice()) {
			fusee.addActionListener(e -> {
				action_fusee=true;
				ordre_fusee.setVisible(true);
			});
			ballon.addActionListener(e -> {
				action_ballon=true;
				ordre_ballon.setVisible(true);
			});
		}
	}

		public void addListenerGame() {

			if (!modeBot) addListenerActions();
			retour.addActionListener(e -> {
				for (Component c : conteneurPrincipal.getComponents()) {
					if (c instanceof CarreView) conteneurPrincipal.remove(c);
				}
				this.setSize(1000,800);
				menu.setLayout(null);
				menu.setBackground(Color.LIGHT_GRAY);
				setContentPane(menu);
				revalidate();
				this.addListenerMenu();
			});
			bot.addActionListener(e -> {
				control.joue_bot();
			});

		}




		public void miseAJour() {
			int i=0;
			int j=0;

					for (Component c : conteneurPrincipal.getComponents()) {
						if (c instanceof CarreView) {

						if (j<model.getNiveau().getLar() && i<model.getNiveau().getHau() && model.getNiveau().getCase(i,j).estVisible()) {

								((CarreView)c).setCarreView(model.getNiveau().getCase(i,j),j,i);

							j++;
						}
						if (i<model.getNiveau().getHau() && j>=(model.getNiveau().getLar())) {
							i++;
							j=0;
						}
					}
			}
			afficheScore();
			addListenerActions();
			if (model.getScore()>=model.getNiveau().getPrice() && !modeBot) {
				ballon.setForeground(Color.green);
				fusee.setForeground(Color.green);
			} else {
				ballon.setForeground(Color.red);
				fusee.setForeground(Color.red);
			}
		if (control.jeuGagne()) {
			retour_menu();
			//-------------------------------------------------------------------
			// recolter points avec reste des cubes ?
			//------------------------------------------------------------------
		}
		}

		public void retour_menu() {
			for (Component c : conteneurPrincipal.getComponents()) {
				if (c instanceof CarreView) conteneurPrincipal.remove(c);
			}

			this.setSize(1000,800);
			menu.setLayout(null);
			menu.setBackground(Color.LIGHT_GRAY);
			setContentPane(menu);
			revalidate();
			this.addListenerMenu();
		}



		@Override
		protected void message_gagne() {
			JOptionPane.showMessageDialog(this, "Vous avez gagné !",
                                          "GAGNÉ",
                                          JOptionPane.INFORMATION_MESSAGE);
		}

		protected void niveau_non_debloque() {
			JOptionPane.showMessageDialog(this, "YOU SHALL NOT PASS",
                                          "Niveau non débloqué :(",
                                          JOptionPane.INFORMATION_MESSAGE);
		}

		protected void pas_assez_pts() {
			JOptionPane.showMessageDialog(this, "NOT ENOUGH!!",
                                          "Vous n'avez pas assez de points :(",
                                          JOptionPane.INFORMATION_MESSAGE);
		}


		public  void afficheScore() {
			score.setText("Score : "+model.getScore());
			meilleurScore.setText("Meilleur score : "+model.getMeilleurScore(model.getNiveau().getId()));
			rescued.setText("rescued : "+model.getNiveau().reste_a_sauver()+"/"+model.getNiveau().getToBeRescued());
			model.getNiveau().afficher();

		}




}
