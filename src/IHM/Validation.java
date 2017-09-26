package IHM;

import javax.swing.*;

import Controleur.ControleurConfirmer;
import Controleur.ControleurSupprimerJoueur;

public class Validation extends JFrame {
	
	
	
	
	public Validation() throws Exception{
		
		int ligne = MainWindows.getListejoueur().getSelectedRow();
		
		Object[] options = {"Oui","Non !"};
		int n = JOptionPane.showOptionDialog(this, "Voulez-vous rendre "+MainWindows.getListejoueur().getValueAt(ligne, 1)+" "+MainWindows.getListejoueur().getValueAt(ligne, 2)+" Inactif ?",
				"Validation", JOptionPane.ERROR_MESSAGE,
				JOptionPane.ERROR_MESSAGE,
				null,
				options,
				options[1]);
	
		if (n == 0){
			ControleurSupprimerJoueur.supprimerJoueur((String) MainWindows.getLjoueur().getValueAt(ligne, 1), (String) MainWindows.getListejoueur().getValueAt(ligne, 2));
		}
		
		
	}

}
