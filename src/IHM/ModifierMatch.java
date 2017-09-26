package IHM;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;

import javax.swing.*;

import Controleur.ControleurModificationMatch;
import Controleur.ControleurModificationPaireBD;
import Main.Tournois;
import Main.database;

public class ModifierMatch extends JFrame{
	
	private JComboBox paire1, paire2;
	private int id;
	private String[][] listeequipe;
	private String[] listesimple;
	
	
	public ModifierMatch(int idmatch, String pairenom1, String pairenom2, int tour) throws Exception{
		super("Modifier paire");
		
		ResultSet resultat = database.select("select e1.idequipe, j1.nom, j1.prenom, j2.nom as nom2, j2.prenom as prenom2"
				+ " from joueur j1, joueur j2, equipe e1"
				+ " where e1.idjoueur1 = j1.idjoueur"
				+ " and e1.idjoueur2 = j2.idjoueur"
				+ " and numerotour = "+tour+"");
		

		setListeequipe(new String[MainWindows.getListeequipe().getRowCount()][2]);
		setListesimple(new String[MainWindows.getListeequipe().getRowCount()]);
		
		int i = 0;
		while(resultat.next()){
			int y = 0;
			this.getListeequipe()[i][y++] = resultat.getString("idequipe");
			this.getListeequipe()[i][y++] = resultat.getString("nom") +" "+ resultat.getString("prenom") +" & "+resultat.getString("nom2") +" "+ resultat.getString("prenom2");
			this.getListesimple()[i]= this.getListeequipe()[i][1];
			i++;
		}
		
		
		
		
		
		this.paire1 = new JComboBox(getListesimple());
		this.paire2 = new JComboBox(getListesimple());
		this.id = idmatch;
		
		this.paire1.setSelectedItem(pairenom1);
		this.paire2.setSelectedItem(pairenom2);
		
		
		JPanel principal = new JPanel(new GridLayout(3, 1));
		
		JLabel numeroequipe = new JLabel("Paire Id : "+this.id);
		JLabel nompaire1 = new JLabel("Paire 1 : ");
		JLabel nompaire2 = new JLabel("Paire 2 : ");
		JLabel tscore = new JLabel("  Score : ");
		JLabel tscore2 = new JLabel("  Score : ");
		
		JTextField score1 = new JTextField("0");
		score1.setPreferredSize(new Dimension(50, 20));
		JTextField score2 = new JTextField("0");
		
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ControleurModificationMatch(paire1, paire2, score1, score2, idmatch, this));
		
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		
		
	
		
		//principal.add(numeroequipe);
		p1.add(nompaire1);
		p1.add(paire1);
		p1.add(tscore);
		p1.add(score1);
		p2.add(nompaire2);
		p2.add(paire2);
		p2.add(tscore2);
		p2.add(score2);
		p3.add(valider);
		
		principal.add(p1);
		principal.add(p2);
		principal.add(p3);
		
		

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		
	}


	public String[] getListesimple() {
		return listesimple;
	}


	public void setListesimple(String[] listesimple) {
		this.listesimple = listesimple;
	}


	public String[][] getListeequipe() {
		return listeequipe;
	}


	public void setListeequipe(String[][] listeequipe) {
		this.listeequipe = listeequipe;
	}


}
