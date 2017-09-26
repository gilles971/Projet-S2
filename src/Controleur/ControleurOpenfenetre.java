package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IHM.ChoixTournois;
import IHM.SupprimerTournois;

public class ControleurOpenfenetre implements ActionListener {

	private String nom;
	
	
	public ControleurOpenfenetre(String nom) {
		this.nom = nom;
	}

	
	

	public void actionPerformed(ActionEvent arg0) {
		
		if(nom == "tournois"){
			try {
				new ChoixTournois();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(this.nom == "suppr"){
			try {
				new SupprimerTournois();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
