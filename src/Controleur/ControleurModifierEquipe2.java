package Controleur;

import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JTable;

import IHM.MainWindows;
import Main.database;
import Table.TableEquipe;

public class ControleurModifierEquipe2 implements ItemListener {
	
	private JTable table;
	private JComboBox box;
	private String[][] joueursimple;

	public ControleurModifierEquipe2(JTable table, JComboBox box, String[][] joueur){
		this.table = table;
		this.box = box;
		this.joueursimple = joueur;
	}
	
	
	
	
	public void itemStateChanged(ItemEvent e) {
		Point x = this.box.getLocation();
		int ligne = MainWindows.getListeequipe().getSelectedRow();
		int cologne = MainWindows.getListeequipe().getSelectedColumn();
		
		x = this.box.getLocation();
		
		System.out.println(x);
		System.out.println(MainWindows.getListeequipe().rowAtPoint(x));
		
		int index = MainWindows.getListeequipe().rowAtPoint(x);
		
//		String joueur =  this.joueursimple[box.getSelectedIndex()][0] + " " + this.joueursimple[box.getSelectedIndex()][1];
//		MainWindows.getListeequipe().setValueAt(null, ligne, cologne);
//		MainWindows.getListeequipe().repaint();
		
		int id = Integer.parseInt(this.joueursimple[box.getSelectedIndex()][2]);
		
		try {
			database.update("update equipe set idjoueur2 = "+id+" where idequipe="+MainWindows.getListeequipe().getValueAt(MainWindows.getListeequipe().rowAtPoint(x), 0)+"");
			x = null;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.table.repaint();
		
			
		
		
	}

}
