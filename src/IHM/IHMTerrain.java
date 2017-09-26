package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import IHM.*;
import Controleur.ControleurValiderMatch;
import Main.Tournois;
import Main.database;

public class IHMTerrain extends JPanel{
	
	
	private JLabel joueur1equipe1, joueur2equipe1, joueur1equipe2, joueur2equipe2;
	private int idjoueur1equipe1, idjoueur2equipe1, idjoueur1equipe2, idjoueur2equipe2;
	private JTextField scoreequipe1, scoreequipe2;
	private PanelImage terrain;
	private JButton valider;
	private int numterrain, idmatch;
	
	
	

	
	public IHMTerrain(int numeroterrain) throws Exception{
		super();
		
		terrain = new PanelImage(new ImageIcon(this.getClass().getResource("terrainbleu.png")).getImage());
		joueur1equipe1 = new JLabel();
		joueur2equipe1 = new JLabel();
		joueur1equipe2 = new JLabel();
		joueur2equipe2 = new JLabel();
		JLabel numeruduterrain = new JLabel("<html><b>Terrain N°"+numeroterrain+"</b></html>");
		Font f = new Font("Arial", Font.PLAIN, 15);
		numeruduterrain.setFont(f);
		numeruduterrain.setForeground(Color.WHITE);
		valider = new JButton("Valider score");
		valider.setToolTipText("Valide et bloque le score d'un match");
		this.numterrain = numeroterrain;
		
		scoreequipe1 = new JTextField("0");
		scoreequipe2 = new JTextField("0");

		terrain.setLayout(null);
		
		getTerrain().setPreferredSize(new Dimension(200, 305));
		getTerrain().setBorder((new BevelBorder(BevelBorder.RAISED)));
		
		
		
		terrain.add(joueur1equipe1);
		terrain.add(joueur2equipe1);
		terrain.add(joueur1equipe2);
		terrain.add(joueur2equipe2);
		
		scoreequipe1.setBorder((new BevelBorder(BevelBorder.RAISED)));
		scoreequipe2.setBorder((new BevelBorder(BevelBorder.RAISED)));
		
		scoreequipe1.setBounds(85, 120, 30, 20);
		scoreequipe2.setBounds(85, 165, 30, 20);
		
		numeruduterrain.setBounds(60, 0, 100, 40);
		
		terrain.add(numeruduterrain);
		
		joueur1equipe1.setBounds(80, 40, 100, 20);
		joueur2equipe1.setBounds(80, 60, 100, 20);
		joueur1equipe2.setBounds(80, 220, 100, 20);
		joueur2equipe2.setBounds(80, 245, 100, 20);
		valider.setBounds(46, 280, 110, 20);
		
		
		
		//Partie r�cup�ration des donn�es
		if(Tournois.getNom() != null){
			try{
		ResultSet resultat = database.select("select j1.nom, j1.idjoueur, j2.nom as nom2, j2.idjoueur as idjoueur2, j3.nom as nom3, j3.idjoueur as idjoueur3, j4.nom as nom4, j4.idjoueur as idjoueur4, "
				+ " m.idmatch from joueur j1, joueur j2, joueur j3, joueur j4, equipe e1, equipe e2, match m"
				+ " where m.idequipe1 = e1.idequipe and m.idequipe2 = e2.idequipe and e1.idjoueur1 = j1.idjoueur"
				+ " and e1.idjoueur2 = j2.idjoueur and e2.idjoueur1 = j3.idjoueur and e2.idjoueur2 = j4.idjoueur "
				+ "and terrain = "+this.numterrain+" and numtour = "+Tournois.getNbToursEffectif()+" and matchjoue = 0");
		
		
		int i = 0;
		
		while(resultat.next() && i == 0){
			joueur1equipe1.setText(resultat.getString("nom"));
			joueur2equipe1.setText(resultat.getString("nom2"));
			joueur1equipe2.setText(resultat.getString("nom3"));
			joueur2equipe2.setText(resultat.getString("nom4"));
			idjoueur1equipe1 = resultat.getInt("idjoueur");
			idjoueur2equipe1 = resultat.getInt("idjoueur2");
			idjoueur1equipe2 = resultat.getInt("idjoueur3");
			idjoueur2equipe2 = resultat.getInt("idjoueur4");
			idmatch = resultat.getInt("idmatch");
			terrain.add(scoreequipe1);
			terrain.add(scoreequipe2);
			terrain.add(valider);
			this.terrain.img = new ImageIcon(this.getClass().getResource("terrain.jpg")).getImage();
			i++;
		}
		
			}catch(Exception e){
		
			}
		
		}
		
		
		//
		
		valider.addActionListener(new ControleurValiderMatch(terrain, idjoueur1equipe1, idjoueur2equipe1, idjoueur1equipe2, idjoueur2equipe2, 
				joueur1equipe1, joueur2equipe1, joueur1equipe2, joueur2equipe2, scoreequipe1, scoreequipe2, numterrain, idmatch, valider));
		
	}





	public JPanel getTerrain() {
		return terrain;
	}





	public void setTerrain(PanelImage terrain) {
		this.terrain = terrain;
	}





	public int getNumterrain() {
		return numterrain;
	}





	public void setNumterrain(int numterrain) {
		this.numterrain = numterrain;
	}
	
	
	
}
