package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import IHM.MainWindows;
import Main.Joueur;
import Main.Tournois;
import Main.database;
import Table.TableJoueur;
import Thread.ThreadJoueur;

public class JoueurControleur implements ActionListener {

	private JLabel message;
	private JTextField nom;
	private JTextField prenom;
	private JTextField age;
	private JComboBox sexe;
	private JComboBox anciente;
	private JComboBox niveau;
	
	
	public JoueurControleur(JLabel message, JTextField nom2, JTextField prenom2, JTextField age2, JComboBox sexe2, 
			JComboBox anciente2, JComboBox niveau2)
	{		
		this.message = message;
		this.nom = nom2;
		this.prenom = prenom2;
		this.age=age2;
		this.sexe=sexe2;
		this.anciente=anciente2;
		this.niveau=niveau2;
	}
	
	
	public void actionPerformed(ActionEvent e){
		
		int age3 = Integer.parseInt(this.age.getText());
		int compteur = 0;
		Object s = this.nom.getText();
		Object ss = this.prenom.getText();
		
		for(int i = 0; i <= MainWindows.getListejoueur().getRowCount()-1; i++){
			if(MainWindows.getLjoueur().getValueAt(i, 1) != null
			   &&MainWindows.getLjoueur().getValueAt(i, 1).equals(s)
			   && MainWindows.getLjoueur().getValueAt(i, 2).equals(ss) == true){
				compteur++;
			}
		}
		
		if(compteur == 0){
		ThreadJoueur j = new ThreadJoueur(this.message, this.nom.getText(), this.prenom.getText(), (String) this.sexe.getSelectedItem(), age3,
			(String) this.anciente.getSelectedItem(), (String) this.niveau.getSelectedItem(), e);
		
		j.start();
		}
		else if(compteur > 0){
			this.message.setText("Le Joueur existe déjà !");
			this.message.setForeground(Color.RED);
		}
		

	}
 
	
	
}
