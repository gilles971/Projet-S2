package Controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import IHM.MainWindows;

public class ControleurLigneSelectione implements MouseListener {

	private int ligne;
	
	public void mouseClicked(MouseEvent e) {
//		MainWindows.setLigne(MainWindows.getListeequipe().getSelectedRow());
//		System.out.println(MainWindows.getLigne());
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
//		MainWindows.setLigne(MainWindows.getListeequipe().getSelectedRow());
//		System.out.println(MainWindows.getLigne());
		
	}

	public void mousePressed(MouseEvent e) {
		MainWindows.setLigne(MainWindows.getListeequipe().getSelectedRow());
		System.out.println(MainWindows.getLigne());
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
