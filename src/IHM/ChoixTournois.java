package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.*;

import Controleur.ControleurCreerTournois;
import Controleur.ControleurSelectionTournois;
import Main.Tournois;
import Main.database;

public class ChoixTournois extends JFrame {

	public ChoixTournois() throws Exception{
		super("Les Tournois");
		
		//Panel haut
		JPanel haut = new JPanel();
		JPanel haut2 = new JPanel();
		haut2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		haut.setLayout(new FlowLayout(FlowLayout.LEFT));
		haut2.setBorder(BorderFactory. createTitledBorder("Liste des Tournois"));
		JComboBox liste = new JComboBox(Tournois.listeTournois());
		JLabel texte = new JLabel("SÃ©lectionner un Tournoi :       ");
		JButton select = new JButton("SÃ©lectionner");
		select.setToolTipText("Charge le tournoi sélectioné");
		
		//
		
		//Panel bas
		JPanel bas = new JPanel();
		JPanel bas2 = new JPanel();
		bas2.setBorder(BorderFactory. createTitledBorder("CrÃ©er un Tournoi"));
		bas.setLayout(new GridLayout(2, 2));
		bas2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel nomtournois = new JLabel("Nom du tournoi : ");
		JTextField nom = new JTextField();
		JLabel nbterrains = new JLabel("Nombre de terrains :    ");
		JTextField terrain = new JTextField();
		nom.setPreferredSize(new Dimension(100, 20));
		terrain.setPreferredSize(new Dimension(100, 20));
		JButton confirmer = new JButton("Confirmer");
		confirmer.setToolTipText("Créer un nouveau tournoi");
		bas.add(nomtournois);
		bas.add(nom);
		bas.add(nbterrains);
		bas.add(terrain);
		bas2.add(bas);
		bas2.add(confirmer);
		
		
		//
		
		if (Tournois.nbtournois() == 0){
			haut2.add(new JLabel("Votre base ne contient aucun tournoi"));
			}
			else{
			haut.add(texte);
			haut.add(liste);
			haut2.add(haut);
			haut2.add(select);
			}
		
		
		
		
		
		//Panel principal
		JPanel principal = new JPanel();
		principal.setLayout(new GridLayout(2, 1));
		principal.add(haut2);
		principal.add(bas2);
		//
		
		//controleur
		confirmer.addActionListener(new ControleurCreerTournois(nom, terrain, this));
		select.addActionListener(new ControleurSelectionTournois(liste, this));
		//
		
		
		
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	
	
	
	public static void main (String[] args) throws Exception{
		new ChoixTournois();
	}
	
	
}
