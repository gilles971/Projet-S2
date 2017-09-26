package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import IHM.IHMTerrain;
import IHM.MainWindows;
import Main.Match;
import Main.Tour;
import Main.Tournois;
import Main.database;
import Thread.ThreadMatch;
import Thread.ThreadSelectionTournois;

public class ControleurCreerMatch implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		try {
			System.gc();
			new ThreadMatch(e).start();
//			MainWindows.getCreermatch().setEnabled(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
