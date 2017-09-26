package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IHM.MainWindows;
import IHM.Validation;
import Main.database;

public class ControleurSupprimerJoueur implements ActionListener{

	private static ActionEvent e;
	
	
	
	public void actionPerformed(ActionEvent e) {
		try {
			new Validation();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.e = e;
		
	}
	
	public static void supprimerJoueur(String nom, String prenom) throws Exception{
		database.update("update joueur set actif = 1-actif where nom = '"+nom+"' and prenom = '"+prenom+"' ");
		MainWindows.maj(ControleurSupprimerJoueur.e);
	}

}
