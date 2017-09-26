package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IHM.AddJoueur;

public class ControleurAddJoueur implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		new AddJoueur();

	}

}
