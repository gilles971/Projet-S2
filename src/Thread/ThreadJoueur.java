package Thread;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.*;

import IHM.MainWindows;
import Main.Joueur;
import Main.Tournois;

public class ThreadJoueur extends Thread {
	
	
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	private String anciente;
	private String niveau;
	private ActionEvent ac;
	private JLabel message;
	
	
	
	
	public ThreadJoueur(JLabel message, String nom, String prenom, String sexe, int age, String experience, String niveau, ActionEvent e){
		this.nom = nom;
		this.prenom = prenom;
		this.age=age;
		this.sexe=sexe;
		this.anciente=experience;
		this.niveau=niveau;
		this.ac = e;
		this.message = message;
	}
	
	public void run(){
		try {
			boolean reussi = Joueur.AjouterJoueur(this.nom, this.prenom, this.sexe, age, this.anciente, this.niveau);
			if(reussi == true){
				this.message.setText("Joueur ajouté !");
				this.message.setForeground(Color.GREEN);
			}
			else if(Tournois.getNom() == null){
				this.message.setText("Sélectionnez un tournoi !");
				this.message.setForeground(Color.RED);
				
			}
			else{
				this.message.setText("Erreur !");
				this.message.setForeground(Color.RED);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			MainWindows.maj(this.ac);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
