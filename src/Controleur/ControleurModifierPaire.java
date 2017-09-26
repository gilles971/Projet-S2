package Controleur;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import IHM.MainWindows;
import IHM.ModifierPaire;
import Table.TableEquipe;

public class ControleurModifierPaire implements MouseListener{

	public void mouseClicked(MouseEvent e) {
				
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		if (e.getClickCount() == 2) {
			Point position = MainWindows.getListeequipe().getMousePosition();
			int ligne = MainWindows.getListeequipe().rowAtPoint(position);
			new ModifierPaire(Integer.parseInt((String) MainWindows.getListeequipe().getValueAt(ligne, 0)), TableEquipe.getJoueursimple(), (String)MainWindows.getListeequipe().getValueAt(ligne, 1), (String)MainWindows.getListeequipe().getValueAt(ligne, 2));
		}

		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
