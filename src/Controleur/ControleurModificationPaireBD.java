package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import IHM.MainWindows;
import IHM.ModifierPaire;
import Main.Equipe;
import Main.database;
import Table.TableEquipe;;

public class ControleurModificationPaireBD implements ActionListener {
	
	private JComboBox j1, j2;
	private int id;
	private String[][] joueursimple;
	private ModifierPaire frame;
	
	
	public ControleurModificationPaireBD(JComboBox j1, JComboBox j2, int id, ModifierPaire frame) {
		this.j1 = j1;
		this.j2 = j2;
		this.id = id;
		this.frame = frame;
	}

	
	

	public void actionPerformed(ActionEvent e) {
		
	
		try {
			
			ResultSet resultat3 = database.select("select count(*) from joueur where actif = 1");
			int nombre2 = 0;
			while(resultat3.next()){
				nombre2 = resultat3.getInt(""); 
			}
			
			joueursimple = new String[nombre2][3];
			
			
			
			ResultSet resultat2 = database.select("select idjoueur, nom, prenom from joueur where actif = 1");
			int ii=0;
			while(resultat2.next()){
				int yy = 0;
				joueursimple[ii][yy++] = resultat2.getString("nom");
				joueursimple[ii][yy++] = resultat2.getString("prenom");
				joueursimple[ii][yy++] = resultat2.getString("idjoueur");
				ii++;
			}
			
			int idjoueur1 = Integer.parseInt(joueursimple[j1.getSelectedIndex()][2]);
			int idjoueur2 = Integer.parseInt(joueursimple[j2.getSelectedIndex()][2]);
			database.update("update equipe set idjoueur1 = "+idjoueur1+" where idequipe = "+id+"");
			database.update("update equipe set idjoueur2 = "+idjoueur2+" where idequipe = "+id+"");
			MainWindows.maj(e);
			frame.dispose();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		
	}
	
	

}
