package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import IHM.MainWindows;
import Main.Tour;
import Table.TableEquipe;
import Table.TableJoueur;

public class ControleurChoixJoueurActif implements ActionListener {

	private JFrame fen;
	private JComboBox box;
	
	public ControleurChoixJoueurActif(JFrame fen, JComboBox box){
		this.fen = fen;
		this.box = box;
		
	}
	
	
	public void actionPerformed(ActionEvent e) {

		try {
			MainWindows.getLjoueur().setDataVector(TableJoueur.getDonneesJoueur((String)MainWindows.getListejoueuractif().getSelectedItem()), TableEquipe.titre);
			MainWindows.getListejoueur().repaint();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
