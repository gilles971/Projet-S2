package IHM;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import Controleur.ControleurChoixJoueurActif;
import Controleur.ControleurChoixTournois;
import Controleur.ControleurChrono;
import Controleur.ControleurCreerEquipe;
import Controleur.ControleurCreerMatch;
import Controleur.ControleurLigneSelectione;
import Controleur.ControleurModifierMatch;
import Controleur.ControleurModifierPaire;
import Controleur.ControleurOpenfenetre;
import Main.Chrono;
import Main.Joueur;
import Main.Tour;
import Main.Tournois;
import Main.database;
import Table.TableDeMatch;
import Table.TableDeModel;
import Table.TableEquipe;
import Table.TableJoueur;
public class MainWindows extends JFrame {


	private static JTable listejoueur;
	private static JTable listeequipe;
	private static JTable listematch;
	private static TableDeModel lmatch;
	private static JLabel co, nombre, Nbpairres;
	private static TableDeModel ljoueur;
	private static TableDeModel lequipe;
	private static JComboBox listetour, listetourmatch, listejoueuractif;
	private static IHMTerrain t;
	private static JPanel match;
	private static JScrollPane scrollmatch;
	public static Chrono chrono2;
	public static JProgressBar barequipe, barmatch;
	private static int ligne;
	private static JButton creermatch;
	
	
	
	public MainWindows() throws Exception{
		super("Gestion");
		
	
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResource("icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		database.premier();
		
		
		
		//
		
		//center haut
		
		//
		
		//Center bas
		
			//Panel Joueur
			JPanel joueur = new JPanel();
			

			JPanel haut = new JPanel();
			haut.setLayout(new BorderLayout());
			JPanel haut2 = new JPanel();
			haut2.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			JButton afficher2 = new JButton("Afficher");
			String[] actifitem = {"Actif", "Inactif", "Tous"};
			setListejoueuractif(new JComboBox(actifitem));
			JLabel texte2 = new JLabel("Joueur :");
			
			haut2.add(texte2);
			haut2.add(getListejoueuractif());
			haut2.add(afficher2);
			
			haut.add(haut2, BorderLayout.NORTH);
			
			
			joueur.setLayout(new BorderLayout());
			MainWindows.setLjoueur(new TableDeModel(TableJoueur.getDonneesJoueur((String)MainWindows.getListejoueuractif().getSelectedItem()), TableJoueur.titre));
			MainWindows.setListejoueur(new JTable(getLjoueur()));
			
			Font f = new Font("Arial", Font.PLAIN, 13);
			MainWindows.getListejoueur().setFont(f);
			joueur.add(MainWindows.getListejoueur(), BorderLayout.CENTER);
			joueur.add(MainWindows.getListejoueur().getTableHeader(), BorderLayout.NORTH);
			
			afficher2.addActionListener(new ControleurChoixJoueurActif(this, MainWindows.getListejoueuractif()));
			//
			
			//Panel Equipe
			JPanel equipe = new JPanel();
			JPanel hautequipe = new JPanel();
			JPanel hautequipe2 = new JPanel();
			JPanel hautequipe3 = new JPanel();
			JPanel tableequipe = new JPanel();
			tableequipe.setLayout(new BorderLayout());
			hautequipe2.setLayout(new FlowLayout(FlowLayout.RIGHT));
			hautequipe3.setLayout(new FlowLayout(FlowLayout.LEFT));
			hautequipe.setLayout(new BorderLayout());
			equipe.setLayout(new BorderLayout());
			setLequipe(new TableDeModel(TableEquipe.getDonneesEquipe(Tour.nbtour()), TableEquipe.titre));
			setListeequipe(new JTable(MainWindows.getLequipe()));
			listeequipe.setToolTipText("Double clic pour modifier une paire");
			
			MainWindows.getListeequipe().addMouseListener(new ControleurModifierPaire());
			
			tableequipe.add(MainWindows.getListeequipe(), BorderLayout.CENTER);
			tableequipe.add(MainWindows.getListeequipe().getTableHeader(), BorderLayout.NORTH);
			JButton creerequipe = new JButton("CrÃ©er les paires");
			creerequipe.setToolTipText("Lance la création de nouvelles paires pour le tour actuel");
//			
			JButton afficher = new JButton("Afficher");
			listetour = new JComboBox(Tour.listetour());
			JLabel texte = new JLabel("Choisir un tour :");
			
			
			barequipe = new JProgressBar();
			barequipe.setStringPainted(true);
			barequipe.setString("Crï¿½er paires");
			
			
			hautequipe2.add(texte);
			hautequipe2.add(listetour);
			hautequipe2.add(afficher);
			hautequipe3.add(creerequipe);
			hautequipe3.add(barequipe);
			hautequipe.add(hautequipe3, BorderLayout.WEST);
			hautequipe.add(hautequipe2, BorderLayout.CENTER);
			equipe.add(hautequipe, BorderLayout.NORTH);
			afficher.addActionListener(new ControleurChoixTournois(this, listetour, "t"));
			creerequipe.addActionListener(new ControleurCreerEquipe());
			
			//
			
			//Panel Match == terrain enfaite...
			setMatch(new JPanel());
			
			JPanel matchtout = new JPanel(new BorderLayout());
			
			JPanel chrono = new JPanel(new BorderLayout());
			
			JPanel chronogauche = new JPanel();
			chronogauche.setLayout(new FlowLayout(FlowLayout.LEFT));
			JPanel chronodroite = new JPanel();
			chronodroite.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			
			chrono2 = new Chrono(0);
			chrono2.setPreferredSize(new Dimension(400, 30));
			chronogauche.add(chrono2);
			
			JLabel minute = new JLabel("Minutes : ");
			JTextField temps = new JTextField("5");
			temps.setPreferredSize(new Dimension(80, 20));
			JButton demarrer = new JButton("DÃ©marrer");
			JButton pause = new JButton("Pause");
			
			chronodroite.add(minute);
			chronodroite.add(temps);
			chronodroite.add(demarrer);
			chronodroite.add(pause);
			
			demarrer.addActionListener(new ControleurChrono(chrono2, "d", temps, demarrer));
			pause.addActionListener(new ControleurChrono(chrono2, "p", temps, pause));
			
			chrono.add(chronogauche, BorderLayout.WEST);
			chrono.add(chronodroite, BorderLayout.EAST);
			
			matchtout.add(chrono, BorderLayout.NORTH);
			
			getMatch().setLayout(new GridLayout(Tournois.getNbTerrain() % 3 ,3));
	
			//
			
			//JPanel match2
			JPanel match2 = new JPanel();
			JPanel matchhaut = new JPanel();
			matchhaut.setLayout(new FlowLayout(FlowLayout.RIGHT));
			JPanel matchhaut2 = new JPanel();
			matchhaut2.setLayout(new FlowLayout(FlowLayout.LEFT));
			JPanel matchhaut3 = new JPanel();
			matchhaut3.setLayout(new BorderLayout());
			match2.setLayout(new BorderLayout());
			JPanel tablematch = new JPanel();
			tablematch.setLayout(new BorderLayout());
			MainWindows.setLmatch(new TableDeModel(TableDeMatch.getDonneesMatch(Tour.nbtour()), TableDeMatch.titre));
			MainWindows.setListematch(new JTable(getLmatch()));
			
			MainWindows.getListematch().addMouseListener(new ControleurModifierMatch());
			MainWindows.getListematch().setToolTipText("Double clic pour modifier un match");
			
			tablematch.add(MainWindows.getListematch(), BorderLayout.CENTER);
			tablematch.add(MainWindows.getListematch().getTableHeader(), BorderLayout.NORTH);
			
			setCreermatch(new JButton("CrÃ©er les matchs"));
			getCreermatch().setToolTipText("Lance la création de nouveaux matchs pour le tour actuel");
//			creermatch.setEnabled(false);
			
			JButton affichermatch = new JButton("Afficher");
			listetourmatch = new JComboBox(Tour.listetour());
			
			barmatch = new JProgressBar();
			barmatch.setStringPainted(true);

			barmatch.setString("Crï¿½er Matchs");
			

			affichermatch.addActionListener(new ControleurChoixTournois(this, listetourmatch,"m"));
			
			JLabel textematch = new JLabel("Choisir un tour :");
			
			
			
			matchhaut.add(textematch);
			matchhaut.add(listetourmatch);
			matchhaut.add(affichermatch);
			matchhaut2.add(getCreermatch());
			matchhaut2.add(barmatch);
			matchhaut3.add(matchhaut2, BorderLayout.WEST);
			matchhaut3.add(matchhaut, BorderLayout.CENTER);
			match2.add(matchhaut3, BorderLayout.NORTH);
			
			
			getCreermatch().addActionListener(new ControleurCreerMatch());
			
			//
			
			
		JScrollPane scrollmatch2 = new JScrollPane(tablematch);
		scrollmatch2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		match2.add(scrollmatch2, BorderLayout.CENTER);
			
			
		setScrollmatch(new JScrollPane(getMatch()));
		getScrollmatch().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		matchtout.add(getScrollmatch(), BorderLayout.CENTER);
		
		JScrollPane scrollequipe = new JScrollPane(tableequipe);
		scrollequipe.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		equipe.add(scrollequipe, BorderLayout.CENTER);
		
		JScrollPane scrolljoueur = new JScrollPane(joueur);
		scrolljoueur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		haut.add(scrolljoueur, BorderLayout.CENTER);
		
		JTabbedPane onglets = new JTabbedPane();
		onglets.add("Joueurs", haut);
		onglets.addTab("Paires", equipe);
		onglets.add("Matchs", match2);
		onglets.add("Terrains", matchtout);
		
		
		
		
		
		//
		
		
		//Panel sud
		JPanel sud = new JPanel();
		JPanel sud2 = new JPanel(new GridLayout(1, 2));
		JPanel sudgauche= new JPanel(new FlowLayout(FlowLayout.LEFT));
		sud2.setBorder(new BevelBorder(BevelBorder.RAISED));
		sud.setLayout(new FlowLayout(FlowLayout.RIGHT));
		setNbpairres(new JLabel("Nombre de paires possibles restantes : "));
		getNbpairres().setVisible(false);
		
		nombre = new JLabel();
		JLabel etatbase = new JLabel("Base de Données : ");
		this.setCo(new JLabel("Déconnectée"));
		MainWindows.getCo().setForeground(Color.RED);
		sudgauche.add(getNbpairres());
		sudgauche.add(nombre);
		sud.add(etatbase);
		sud.add(co);
		sud2.add(sudgauche);
		sud2.add(sud);
		//
		
		
		//Panel toolbar
				JPanel menupan = new JPanel();
				JPanel toolbar = new JPanel();
				menupan.setLayout(new BorderLayout());
				toolbar.setLayout(new BorderLayout());
				JToolBar tool = new IHMToolBar(this);
				JMenuBar bar = new JMenuBar();
				JMenu menu = new JMenu("Fichier");
				JMenu edition = new JMenu("Editer");
				JMenuItem supprtournois = new JMenuItem("Supprimer un tournoi");
				supprtournois.addActionListener(new ControleurOpenfenetre("suppr"));
				edition.add(supprtournois);
				bar.add(menu);
				bar.add(edition);
				
				
				

				menupan.add(bar, BorderLayout.NORTH);
				toolbar.add(tool, BorderLayout.NORTH);
		
		//Panel Principal
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		principal.add(menupan, BorderLayout.NORTH);
		principal.add(toolbar, BorderLayout.WEST);
		principal.add(onglets, BorderLayout.CENTER);
		principal.add(sud2, BorderLayout.SOUTH);
		
		//
	
	
		
		
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	
	
	public static void maj(ActionEvent e) throws Exception{
		MainWindows.getLjoueur().setDataVector(TableJoueur.getDonneesJoueur((String)MainWindows.getListejoueuractif().getSelectedItem()), TableJoueur.titre);
		MainWindows.getListejoueur().repaint();
		MainWindows.getLequipe().setDataVector(TableEquipe.getDonneesEquipe(Tour.nbtour()), TableEquipe.titre);
		MainWindows.getListeequipe().repaint();
		MainWindows.getLmatch().setDataVector(TableDeMatch.getDonneesMatch(Tour.nbtour()), TableDeMatch.titre);
		MainWindows.getListematch().repaint();
		listetour.removeAllItems();
		listetourmatch.removeAllItems();
		String[] tmp = Tour.listetour();
		for(int i = 0; i<=tmp.length-1; i++){
		listetour.addItem(tmp[i]);
		listetourmatch.addItem(tmp[i]);
		listetourmatch.setSelectedIndex(i);
		listetour.setSelectedIndex(i);
		}
		
		Tournois.setNbJoueursNouveau();
		Tournois.setNbJoueursAncien();
		
		MainWindows.nombre.setText(Integer.toString((Tournois.getNbJoueursNouveau()*Tournois.getNbJoueursAncien())-Tournois.getNombrepaires()));
		
		MainWindows.listetour.repaint();
		match.repaint();
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		new MainWindows();

	}


	public static JTable getListejoueur() {
		return listejoueur;
	}


	public static void setListejoueur(JTable listejoueur) {
		MainWindows.listejoueur = listejoueur;
	}


	public static TableDeModel getLjoueur() {
		return ljoueur;
	}


	public static void setLjoueur(TableDeModel ljoueur) {
		MainWindows.ljoueur = ljoueur;
	}


	public static JLabel getCo() {
		return co;
	}


	public void setCo(JLabel co) {
		this.co = co;
	}


	public static TableDeModel getLequipe() {
		return lequipe;
	}


	public static void setLequipe(TableDeModel lequipe) {
		MainWindows.lequipe = lequipe;
	}


	public static JTable getListeequipe() {
		return listeequipe;
	}


	public static void setListeequipe(JTable listeequipe) {
		MainWindows.listeequipe = listeequipe;
	}

	public static JComboBox getListetour() {
		return MainWindows.listetour;
	}


	public static JPanel getMatch() {
		return match;
	}


	public void setMatch(JPanel match) {
		this.match = match;
	}


	public static JScrollPane getScrollmatch() {
		return scrollmatch;
	}


	public void setScrollmatch(JScrollPane scrollmatch) {
		this.scrollmatch = scrollmatch;
	}


	public static JTable getListematch() {
		return listematch;
	}


	public static void setListematch(JTable listematch) {
		MainWindows.listematch = listematch;
	}


	public static TableDeModel getLmatch() {
		return lmatch;
	}


	public static void setLmatch(TableDeModel lmatch) {
		MainWindows.lmatch = lmatch;
	}


	public static JComboBox getListejoueuractif() {
		return listejoueuractif;
	}


	public static void setListejoueuractif(JComboBox listejoueuractif) {
		MainWindows.listejoueuractif = listejoueuractif;
	}
	
	public void throwError(String msg){
		JOptionPane.showMessageDialog(this, msg,"Erreur",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static int getLigne() {
		return ligne;
	}


	public static void setLigne(int ligne) {
		MainWindows.ligne = ligne;
	}


	public static JButton getCreermatch() {
		return creermatch;
	}


	public void setCreermatch(JButton creermatch) {
		this.creermatch = creermatch;
	}


	public static JLabel getNbpairres() {
		return Nbpairres;
	}


	public static void setNbpairres(JLabel nbpairres) {
		Nbpairres = nbpairres;
	}

}
