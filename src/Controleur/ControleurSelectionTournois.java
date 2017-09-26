package Controleur;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import IHM.ChoixTournois;
import IHM.IHMTerrain;
import IHM.MainWindows;
import Main.Tour;
import Main.Tournois;
import Thread.ThreadSelectionTournois;

public class ControleurSelectionTournois implements ActionListener {

		private JComboBox nom;
		private JFrame fen;
	
	
	public ControleurSelectionTournois(JComboBox nom, JFrame fen){
		this.nom = nom;
		this.fen = fen;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		Tournois.setNom((String) this.nom.getSelectedItem());
		try {
			int nbtour = Tour.nbtour();
			Tournois.setNbToursEffectif(nbtour);
			MainWindows.maj(e);
			
			ThreadSelectionTournois t = new ThreadSelectionTournois(e);
			t.start();
			
			MainWindows.getCo().setText("Connecté");
			MainWindows.getCo().setForeground(Color.GREEN);
			
			MainWindows.getNbpairres().setVisible(true);
			
			fen.dispose();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

	}

}
