package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import IHM.MainWindows;
import Main.Tour;
import Table.TableDeMatch;
import Table.TableEquipe;
public class ControleurChoixTournois implements ActionListener {

	private JFrame fen;
	private JComboBox box;
	private String nom;
	
	public ControleurChoixTournois(JFrame fen, JComboBox box, String nom){
		this.fen = fen;
		this.box = box;
		this.nom = nom;
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(this.nom == "t"){
		int numtour = Integer.parseInt((String) box.getSelectedItem());
		try {
			MainWindows.getLequipe().setDataVector(TableEquipe.getDonneesEquipe(numtour), TableEquipe.titre);
			MainWindows.getListeequipe().repaint();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	else if(this.nom == "m"){
		int numtour = Integer.parseInt((String) box.getSelectedItem());
		try {
			MainWindows.getLmatch().setDataVector(TableDeMatch.getDonneesMatch(numtour), TableDeMatch.titre);
			MainWindows.getListematch().repaint();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	}
		
		
}
