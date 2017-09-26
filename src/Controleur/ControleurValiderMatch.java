package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import IHM.IHMTerrain;
import IHM.MainWindows;
import IHM.PanelImage;
import Main.Tournois;
import Main.database;

public class ControleurValiderMatch implements ActionListener {
	
	private JLabel joueur1equipe1, joueur2equipe1, joueur1equipe2, joueur2equipe2;
	private int idjoueur1equipe1, idjoueur2equipe1, idjoueur1equipe2, idjoueur2equipe2;
	private JTextField scoreequipe1, scoreequipe2;
	private int idmatch, numterrain;
	PanelImage terrain;
	JButton valider;
		
	

	public ControleurValiderMatch(PanelImage terrain, int idjoueur1equipe1, int idjoueur2equipe1, int idjoueur1equipe2, int idjoueur2equipe2, JLabel joueur1equipe1, JLabel joueur2equipe1, JLabel joueur1equipe2, JLabel joueur2equipe2, JTextField scoreequipe1, JTextField scoreequipe2, int numterrain, int idmatch, JButton valider){
		this.idjoueur1equipe1 = idjoueur1equipe1;
		this.idjoueur2equipe1 = idjoueur2equipe1;
		this.idjoueur1equipe2 = idjoueur1equipe2;
		this.idjoueur2equipe2 = idjoueur2equipe2;
		this.joueur1equipe1 = joueur1equipe1;
		this.joueur2equipe1 = joueur2equipe1;
		this.joueur1equipe2 = joueur1equipe2;
		this.joueur2equipe2 = joueur2equipe2;
		this.scoreequipe1 = scoreequipe1;
		this.scoreequipe2 = scoreequipe2;
		this.terrain = terrain;
		this.numterrain = numterrain;
		this.idmatch = idmatch;
		this.valider = valider;
			
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		try {
			database.update("update match set matchjoue = 1 where idmatch = "+idmatch+"");
			database.update("update joueur set score = score + "+scoreequipe1.getText()+" where idjoueur = "+idjoueur1equipe1+"  or idjoueur = "+idjoueur2equipe1+"");
			database.update("update joueur set score = score + "+scoreequipe2.getText()+" where idjoueur = "+idjoueur1equipe2+"  or idjoueur = "+idjoueur2equipe2+"");
			database.update("update match set pointequipe1 = "+scoreequipe1.getText()+" where idmatch = "+idmatch+" ");
			database.update("update match set pointequipe2 = "+scoreequipe2.getText()+" where idmatch = "+idmatch+" ");
	
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	
		
		try {
			ResultSet resultat = database.select("select j1.nom, j1.idjoueur, j2.nom as nom2, j2.idjoueur as idjoueur2, j3.nom as nom3, j3.idjoueur as idjoueur3, j4.nom as nom4, j4.idjoueur as idjoueur4, "
					+ "m.idmatch from joueur j1, joueur j2, joueur j3, joueur j4, equipe e1, equipe e2, match m"
					+ " where m.idequipe1 = e1.idequipe and m.idequipe2 = e2.idequipe and e1.idjoueur1 = j1.idjoueur"
					+ " and e1.idjoueur2 = j2.idjoueur and e2.idjoueur1 = j3.idjoueur and e2.idjoueur2 = j4.idjoueur "
					+ "and terrain = "+numterrain+" and numtour = "+Tournois.getNbToursEffectif()+" and matchjoue =0");
			
			int i = 0;
			

			this.joueur1equipe1.setText("");
			this.joueur2equipe1.setText("");
			this.joueur1equipe2.setText("");
			this.joueur2equipe2.setText("");
			this.scoreequipe1.setVisible(false);
			this.scoreequipe2.setVisible(false);
			valider.setVisible(false);
			this.terrain.img = new ImageIcon(this.getClass().getResource("terrainbleu.png")).getImage();
			
			
				while(resultat.next() && i == 0){
					this.joueur1equipe1.setText(resultat.getString("nom"));
					this.joueur2equipe1.setText(resultat.getString("nom2"));
					this.joueur1equipe2.setText(resultat.getString("nom3"));
					this.joueur2equipe2.setText(resultat.getString("nom4"));
					idjoueur1equipe1 = resultat.getInt("idjoueur");
					idjoueur2equipe1 = resultat.getInt("idjoueur2");
					idjoueur1equipe2 = resultat.getInt("idjoueur3");
					idjoueur2equipe2 = resultat.getInt("idjoueur4");
					this.idmatch = resultat.getInt("idmatch");
					this.scoreequipe1.setText("0");
					this.scoreequipe2.setText("0");
					this.scoreequipe1.setVisible(true);
					this.scoreequipe2.setVisible(true);
					valider.setVisible(true);
					this.terrain.img = new ImageIcon(this.getClass().getResource("terrain.jpg")).getImage();
					this.terrain.repaint();
					MainWindows.getMatch().repaint();
					MainWindows.getScrollmatch().repaint();
					i++;
					
				}
				
				terrain.repaint();
				MainWindows.getMatch().repaint();
				MainWindows.getScrollmatch().repaint();	
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			

	}

}
