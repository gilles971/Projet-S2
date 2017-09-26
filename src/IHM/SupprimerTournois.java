package IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import Controleur.ControleurSupprimerJoueur;
import Main.Tournois;
import Main.database;

public class SupprimerTournois extends JFrame implements ActionListener {
	
	private JComboBox liste;
	
	public SupprimerTournois() throws Exception{
		
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		liste = new JComboBox(Tournois.listeTournois());
		JLabel texte = new JLabel("Sélectionnez un tournois a supprimer : ");
		JLabel texte2 = new JLabel("Aucun Tournoi à supprimer");
		JButton confirmer = new JButton("Confirmer");
		principal.setBorder(BorderFactory. createTitledBorder("Supprimer un Tournoi"));
		
		panel.add(texte);
		panel.add(liste);
		principal.add(panel, BorderLayout.CENTER);
		principal.add(confirmer, BorderLayout.SOUTH);
		
		if(Tournois.nbtournois() == 0){
			panel.remove(texte);
			panel.add(texte2);
			panel.remove(liste);
			principal.remove(confirmer);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
		}
		
		confirmer.addActionListener(this);
		
		
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		
	}

	
	public void actionPerformed(ActionEvent e) {
		
		Object[] options = {"Oui","Non !"};
		int n = JOptionPane.showOptionDialog(this, "Voulez-vous vraiment supprimer "+this.liste.getSelectedItem()+"",
				"Validation", JOptionPane.ERROR_MESSAGE,
				JOptionPane.ERROR_MESSAGE,
				null,
				options,
				options[1]);
	
		if (n == 0){
			try {
				File suppr = new File("db/"+this.liste.getSelectedItem()+".script");
				suppr.delete();
				File suppr2 = new File("db/"+this.liste.getSelectedItem()+".properties");
				suppr2.delete();
				database.updatetournois("delete from tournois where nom = '"+this.liste.getSelectedItem()+"'");
				this.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	

}
