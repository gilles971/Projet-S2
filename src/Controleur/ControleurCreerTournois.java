package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import IHM.MainWindows;
import Main.Tournois;
import Main.database;

public class ControleurCreerTournois implements ActionListener {

	private JTextField nom, nbTerrain;
	private JFrame fen;
	
	
	public ControleurCreerTournois(JTextField nom, JTextField nbTerrain, JFrame fen){
		this.nom = nom;
		this.nbTerrain = nbTerrain;
		this.fen = fen;
	}
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		try {
			database.updatetournois("insert into tournois(nom, nbterrain, numtour) values('"+this.nom.getText()+"', "+Integer.parseInt(this.nbTerrain.getText())+", 0)");
			Tournois.setNom(this.nom.getText());
			database.table();
			MainWindows.getCo().setText("Connect�");
			MainWindows.getCo().setForeground(Color.GREEN);
			MainWindows.maj(e);
			fen.dispose();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
