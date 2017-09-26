package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import IHM.MainWindows;
import Table.TableDeModel;
import Table.TableJoueur;


public class ControleurRefresh implements ActionListener {
	
	private JTable joueur;

	public ControleurRefresh(JTable joueur){
		this.joueur = joueur;
	}
	

	public void actionPerformed(ActionEvent e) {
		try {
			MainWindows.maj(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
