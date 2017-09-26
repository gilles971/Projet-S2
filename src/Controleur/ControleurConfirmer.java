package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IHM.Validation;

public class ControleurConfirmer implements ActionListener {

	private String nom;
	private String prenom;
	private Validation frame;
	
	public ControleurConfirmer(Validation frame, String nom, String prenom) throws Exception{
		this.nom = nom;
		this.prenom = prenom;
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent p) {
		try {
			ControleurSupprimerJoueur.supprimerJoueur(this.nom, this.prenom);
			frame.dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	

	
	
	
}
