package Controleur;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JTable;

import IHM.MainWindows;
import Main.database;
import Table.TableEquipe;

public class ControleurModifierEquipe implements ItemListener {
	
	private JTable table;
	private JComboBox box;
	private String[][] joueursimple;

	public ControleurModifierEquipe(JTable table, JComboBox box, String[][] joueur){
		this.table = table;
		this.box = box;
		this.joueursimple = joueur;
	}
	
	
	
	
	public void itemStateChanged(ItemEvent e) {
		int ligne = MainWindows.getLigne();
		
		int id = Integer.parseInt(this.joueursimple[box.getSelectedIndex()][2]);
		
		System.out.println(MainWindows.getListeequipe().getValueAt(ligne, 0));
		System.out.println(this.joueursimple[box.getSelectedIndex()][2]);
		
	
		
		try {
			database.update("update equipe set idjoueur1 = "+6+" where idequipe="+MainWindows.getListeequipe().getValueAt(ligne, 0)+"");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		this.table.repaint();
		
			
		
		
	}

}
