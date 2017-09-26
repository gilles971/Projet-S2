package Controleur;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import IHM.MainWindows;
import IHM.ModifierMatch;
import IHM.ModifierPaire;
import Table.TableDeMatch;
import Table.TableEquipe;

public class ControleurModifierMatch implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		if (e.getClickCount() == 2) {
			Point position = MainWindows.getListematch().getMousePosition();
			int ligne = MainWindows.getListematch().rowAtPoint(position);
			try {
				new ModifierMatch(Integer.parseInt((String) MainWindows.getListematch().getValueAt(ligne, 0)), (String)MainWindows.getListematch().getValueAt(ligne, 1), (String)MainWindows.getListematch().getValueAt(ligne, 2), Integer.parseInt((String) MainWindows.getListematch().getValueAt(ligne, 4)));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
