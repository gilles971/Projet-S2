package IHM;

import java.awt.GridLayout;

import javax.swing.*;

import Controleur.ControleurModificationPaireBD;

public class ModifierPaire extends JFrame{
	
	private JComboBox joueur1, joueur2;
	private String[][] listejoueur;
	private int id;
	
	
	public ModifierPaire(int idequipe, String[] joueursimple, String joueurnom1, String joueurnom2){
		super("Modifier paire");
		
		this.joueur1 = new JComboBox(joueursimple);
		this.joueur2 = new JComboBox(joueursimple);
		this.listejoueur = listejoueur;
		this.id = id;
		
		this.joueur1.setSelectedItem(joueurnom1);
		this.joueur2.setSelectedItem(joueurnom2);
		
		
		JPanel principal = new JPanel(new GridLayout(3, 2));
		
		JLabel numeroequipe = new JLabel("Paire Id : "+this.id);
		JLabel nomjoueur1 = new JLabel("Joueur 1 : ");
		JLabel nomjoueur2 = new JLabel("Joueur 2 : ");
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ControleurModificationPaireBD(joueur1, joueur2, idequipe, this));
		
		//principal.add(numeroequipe);
		principal.add(nomjoueur1);
		principal.add(joueur1);
		principal.add(nomjoueur2);
		principal.add(joueur2);
		principal.add(valider);
		
		

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		
	}


}
