import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public abstract class Interface extends Vue{



    public static void lancerInterface(Joueur j) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        JButton btn_jouer, musicStop, musicPlay, returnHome, btn_info, frenchVersion, englishVersion;
        JButton p1,p2,p3,p4,p5,p6;
        //-----Files
        File musicFond = new File("Music/MusicFond.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFond);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        //---------------------------------------------Main Screen
        //-----------------Images
        ImageIcon logo = new ImageIcon("Images/Logo2.png");
        ImageIcon img_background = new ImageIcon("Images/gif-bg.gif");
        ImageIcon imgMusicon = new ImageIcon("Images/Music-On.png");
        ImageIcon imgMusicoff = new ImageIcon("Images/Music-Off.png");
        ImageIcon img_fond = new ImageIcon("Images/fond.jpg");
        ImageIcon returnBack = new ImageIcon("Images/return.png");
        ImageIcon info = new ImageIcon("Images/info.png");
        ImageIcon frenchFlag = new ImageIcon("Images/frenchFlag.png");
        ImageIcon englishFlag = new ImageIcon("Images/englishFlag.png");
        ImageIcon planete1 = new ImageIcon("Images/Planete1.jpg");
        ImageIcon planete2 = new ImageIcon("Images/Planete2.jpg");
        ImageIcon planete3 = new ImageIcon("Images/Planete3.jpg");
        ImageIcon planete4 = new ImageIcon("Images/Planete4.jpg");
        ImageIcon planete5 = new ImageIcon("Images/Planete5.jpg");
        ImageIcon planete6 = new ImageIcon("Images/Planete6.jpg");



        // ----------------Labels
        JLabel background = new JLabel();
        background.setText("");
        background.setIcon(img_background);
        //Frame
        JFrame mainScreen = new JFrame();



        //-----
        background.setHorizontalTextPosition(JLabel.CENTER);
        background.setVerticalTextPosition(JLabel.CENTER);
        background.setBounds(0,0,1000,800);
        // ----------------Buttons
        //Jouer
        btn_jouer = new JButton("Jouer");
        btn_jouer.setBounds(650,600,200,80);
        btn_jouer.setFont(new Font("Trebuchet",Font.BOLD,25));
        btn_jouer.setForeground(new Color(176,255,134));
        btn_jouer.setBackground(new Color(0,124,101));
        btn_jouer.setBorder(BorderFactory.createCompoundBorder());
        //Music Stop
        musicStop = new JButton("");
        musicStop.setIcon(imgMusicon);
        musicStop.setBounds(15,150,40,40);
        musicStop.setBackground(new Color(0,124,101));
        musicStop.setBorder(BorderFactory.createCompoundBorder());
        //Music Stop
        musicPlay = new JButton("");
        musicPlay.setIcon(imgMusicoff);
        musicPlay.setBounds(15,150,40,40);
        musicPlay.setBackground(new Color(0,124,101));
        musicPlay.setBorder(BorderFactory.createCompoundBorder());
        //Return Home
        returnHome = new JButton("");
        returnHome.setIcon(returnBack);
        returnHome.setBounds(950,700,40,40);
        returnHome.setBackground(new Color(0,124,101));
        returnHome.setBorder(BorderFactory.createCompoundBorder());
        //Info
        btn_info = new JButton("");
        btn_info.setIcon(info);
        btn_info.setBounds(15,195,40,40);
        btn_info.setBackground(new Color(0,124,101));
        btn_info.setBorder(BorderFactory.createCompoundBorder());
        //French Version
        frenchVersion = new JButton("");
        frenchVersion.setIcon(frenchFlag);
        frenchVersion.setBounds(750,20,40,40);
        frenchVersion.setBackground(new Color(0,124,101));
        frenchVersion.setBorder(BorderFactory.createCompoundBorder());
        //English Version
        englishVersion = new JButton("");
        englishVersion.setIcon(englishFlag);
        englishVersion.setBounds(800,20,40,40);
        englishVersion.setBackground(new Color(0,124,101));
        englishVersion.setBorder(BorderFactory.createCompoundBorder());
        // ----------------Bouttons Planetes
        //----------------Planet 1
        p1 = new JButton("");
        p1.setBounds(190,110,300,200);
        p1.setIcon(planete1);
        p1.setFont(new Font("Trebuchet",Font.BOLD,25));
        p1.setForeground(new Color(176,255,134));
        p1.setBackground(new Color(0,124,101));
        p1.setBorder(BorderFactory.createCompoundBorder());

        //-------------Planet 2
        p2 = new JButton("");
        p2.setBounds(520,110,300,200);
        p2.setIcon(planete2);
        p2.setFont(new Font("Trebuchet",Font.BOLD,25));
        p2.setForeground(new Color(176,255,134));
        p2.setBackground(new Color(0,124,101));
        p2.setBorder(BorderFactory.createCompoundBorder());
        //----------------Planet 3
        p3 = new JButton("");
        p3.setBounds(190,340,300,200);
        p3.setIcon(planete3);
        p3.setFont(new Font("Trebuchet",Font.BOLD,25));
        p3.setForeground(new Color(176,255,134));
        p3.setBackground(new Color(0,124,101));
        p3.setBorder(BorderFactory.createCompoundBorder());
        //----------------Planet 4
        p4 = new JButton("");
        p4.setBounds(520,340,300,200);
        p4.setIcon(planete4);
        p4.setFont(new Font("Trebuchet",Font.BOLD,25));
        p4.setForeground(new Color(176,255,134));
        p4.setBackground(new Color(0,124,101));
        p4.setBorder(BorderFactory.createCompoundBorder());
        //----------------Planet 5
        p5 = new JButton("");
        p5.setBounds(190,570,300,200);
        p5.setIcon(planete5);
        p5.setFont(new Font("Trebuchet",Font.BOLD,25));
        p5.setForeground(new Color(176,255,134));
        p5.setBackground(new Color(0,124,101));
        p5.setBorder(BorderFactory.createCompoundBorder());
        //----------------Planet 6
        p6 = new JButton("");
        p6.setBounds(520,570,300,200);
        p6.setIcon(planete6);
        p6.setFont(new Font("Trebuchet",Font.BOLD,25));
        p6.setForeground(new Color(176,255,134));
        p6.setBackground(new Color(0,124,101));
        p6.setBorder(BorderFactory.createCompoundBorder());


    //-----------------Main Features
     mainScreen.setTitle("Planet Rescue");
        mainScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainScreen.setVisible(true);
        mainScreen.setSize(1000,800);
        mainScreen.setResizable(false);
        mainScreen.setIconImage(logo.getImage());
        mainScreen.add(btn_jouer);
        mainScreen.add(musicStop);
        mainScreen.add(musicPlay);
        musicPlay.setVisible(false);
        mainScreen.add(returnHome);
        returnHome.setVisible(false);
        mainScreen.add(btn_info);
        mainScreen.add(frenchVersion);
        frenchVersion.setVisible(false);
        mainScreen.add(englishVersion);
        englishVersion.setVisible(false);
        mainScreen.add(p1);
        p1.setVisible(false);

        mainScreen.add(p2);
        p2.setVisible(false);
        p2.setEnabled(false);
        mainScreen.add(p3);
        p3.setVisible(false);
        p3.setEnabled(false);
        mainScreen.add(p4);
        p4.setVisible(false);
        p4.setEnabled(false);
        mainScreen.add(p5);
        p5.setVisible(false);
        p5.setEnabled(false);
        mainScreen.add(p6);
        p6.setVisible(false);
        p6.setEnabled(false);
        mainScreen.add(background);


        mainScreen.pack();
        clip.start();
        clip.loop(clip.LOOP_CONTINUOUSLY);


//---------------------------------------------Info Screen English
        //-----------------Images
        ImageIcon theGameFond = new ImageIcon("Images/theGame.jpg");
        // ----------------Labels
        JLabel theGameEn = new JLabel();
        theGameEn.setText("");
        theGameEn.setIcon(theGameFond);
        //-----
        theGameEn.setHorizontalTextPosition(JLabel.CENTER);
        theGameEn.setVerticalTextPosition(JLabel.CENTER);
        theGameEn.setBounds(0,0,1000,800);

        //-----------------Main Features
        mainScreen.add(theGameEn);
        theGameEn.setVisible(false);


        //---------------------------------------------Info Screen French
        //-----------------Images
        ImageIcon leJeuFond = new ImageIcon("Images/leJeu.jpg");
        // ----------------Labels
        JLabel theGameFr = new JLabel();
        theGameFr.setText("");
        theGameFr.setIcon(leJeuFond);
        //-----
        theGameFr.setHorizontalTextPosition(JLabel.CENTER);
        theGameFr.setVerticalTextPosition(JLabel.CENTER);
        theGameFr.setBounds(0,0,1000,800);
        //-----------------Main Features
        mainScreen.add(theGameFr);
        theGameFr.setVisible(false);



        //---------------------------------------------Level Screen
        //-----------------Images
        ImageIcon img_fondLevel = new ImageIcon("Images/fondLevel.jpg");
        // ----------------Labels
        JLabel fond = new JLabel();
        fond.setText("");
        fond.setIcon(img_fondLevel);
        //-----
        fond.setHorizontalTextPosition(JLabel.CENTER);
        fond.setVerticalTextPosition(JLabel.CENTER);
        fond.setBounds(0,0,1000,800);


        //-----------------Main Features
        mainScreen.add(fond);
        fond.setVisible(false);



        //-------------------------------Bouton Jouer du Main Screen
        btn_jouer.addActionListener(e -> {
            background.setVisible(false);
            btn_jouer.setVisible(false);
            fond.setVisible(true);
            returnHome.setVisible(true);
            theGameEn.setVisible(false);
            theGameFr.setVisible(false);
            englishVersion.setVisible(false);
            p1.setVisible(true);


            p2.setVisible(true);
            p3.setVisible(true);
            p4.setVisible(true);
            p5.setVisible(true);
            p6.setVisible(true);


        });
        //-------------------------------Bouton Music Stop
        musicStop.addActionListener(e -> {
            musicStop.setVisible(false);
            musicPlay.setVisible(true);
            clip.stop();
        });
        //-------------------------------Bouton Music Play
        musicPlay.addActionListener(e -> {
            musicStop.setVisible(true);
            musicPlay.setVisible(false);
            clip.start();
        });
        //-------------------------------Bouton Retour
        returnHome.addActionListener(e -> {
            returnHome.setVisible(false);
            background.setVisible(true);
            btn_jouer.setVisible(true);
            fond.setVisible(false);
            frenchVersion.setVisible(false);
            englishVersion.setVisible(false);
            p1.setVisible(false);


            p2.setVisible(false);
            p3.setVisible(false);
            p4.setVisible(false);
            p5.setVisible(false);
            p6.setVisible(false);

        });
        //-------------------------------Bouton Info
        btn_info.addActionListener(e -> {
            returnHome.setVisible(true);
            background.setVisible(false);
            btn_jouer.setVisible(false);
            theGameEn.setVisible(true);
            frenchVersion.setVisible(true);
            englishVersion.setVisible(true);
            englishVersion.setEnabled(false);
            p1.setVisible(false);


            p2.setVisible(false);
            p3.setVisible(false);
            p4.setVisible(false);
            p5.setVisible(false);
            p6.setVisible(false);



        });
        //-------------------------------Bouton French Version
        frenchVersion.addActionListener(e -> {
            returnHome.setVisible(true);
            background.setVisible(false);
            btn_jouer.setVisible(false);
            theGameEn.setVisible(false);
            theGameFr.setVisible(true);
            frenchVersion.setVisible(true);
            frenchVersion.setEnabled(false);
            englishVersion.setVisible(true);
            englishVersion.setEnabled(true);


        });
        //-------------------------------Bouton English Version
        englishVersion.addActionListener(e -> {
            returnHome.setVisible(true);
            background.setVisible(false);
            btn_jouer.setVisible(false);
            theGameEn.setVisible(true);
            theGameFr.setVisible(false);
            frenchVersion.setVisible(true);
            frenchVersion.setEnabled(true);
            englishVersion.setVisible(true);
            englishVersion.setEnabled(false);


        });
        //--------------------Planete 1 bouton
        p1.addActionListener(e -> {

            new Launcher(j);
            mainScreen.dispose();


        });


}}
