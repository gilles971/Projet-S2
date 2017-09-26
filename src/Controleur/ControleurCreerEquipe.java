package Controleur;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import IHM.IHMTerrain;
import IHM.MainWindows;
import Main.Tour;
import Main.Tournois;
import Main.database;
import Thread.ThreadCreerPaire;
import Thread.ThreadSelectionTournois;

public class ControleurCreerEquipe implements ActionListener{

		
	public void actionPerformed(ActionEvent e) {
			System.gc();
			new ThreadCreerPaire().start();
//			MainWindows.getCreermatch().setEnabled(true);
	}

}
