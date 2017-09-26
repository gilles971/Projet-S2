package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import IHM.MainWindows;
import IHM.ModifierMatch;
import IHM.ModifierPaire;
import Main.Equipe;
import Main.Tournois;
import Main.database;
import Table.TableEquipe;;

public class ControleurModificationMatch implements ActionListener {
	
	private JComboBox p1, p2;
	private int id;
	private ModifierMatch frame;
	private JTextField score1, score2;
	
	
	public ControleurModificationMatch(JComboBox p1, JComboBox p2, JTextField score1, JTextField score2, int id, ModifierMatch frame) {
		this.p1 = p1;
		this.p2 = p2;
		this.id = id;
		this.frame = frame;
		this.score1 = score1;
		this.score2 = score2;
	}

	
	

	public void actionPerformed(ActionEvent e) {
		
	
		try {
			
			
			
			int idpaire1 = Integer.parseInt(frame.getListeequipe()[p1.getSelectedIndex()][0]);
			int idpaire2 = Integer.parseInt(frame.getListeequipe()[p2.getSelectedIndex()][0]);
			database.update("update match set idequipe1 = "+idpaire1+" where idmatch = "+id+"");
			database.update("update match set idequipe2 = "+idpaire2+" where idmatch = "+id+"");
			database.update("update match set pointequipe1 = "+score1.getText()+" where idmatch = "+id+"");
			database.update("update match set pointequipe2 = "+score2.getText()+" where idmatch = "+id+"");
			MainWindows.maj(e);
			frame.dispose();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		
	}
	
	

}
