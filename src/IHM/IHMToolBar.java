package IHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import Controleur.ControleurAddJoueur;
import Controleur.ControleurDB;
import Controleur.ControleurExport;
import Controleur.ControleurImport;
import Controleur.ControleurOpenfenetre;
import Controleur.ControleurRefresh;
import Controleur.ControleurSupprimerJoueur;
public class IHMToolBar extends JToolBar {

	private MainWindows frame;
	
	
	public IHMToolBar(MainWindows frame){

		super();
	
		
		this.frame = frame;
		
		
		JButton nouveautournois = new JButton("Tournois", new ImageIcon(this.getClass().getResource("CardboardBoxAdd32.png")));
		JButton coBD = new JButton("Connexion", new ImageIcon(this.getClass().getResource("DatabaseAdd32.png")));
		JButton userAdd = new JButton("Ajouter",new ImageIcon(this.getClass().getResource("UsersAdd32.png")));
		JButton refresh = new JButton("Rafraîchir", new ImageIcon(this.getClass().getResource("Refresh32.png")));
		//JButton editUser = new JButton("Rechercher", new ImageIcon(this.getClass().getResource("UserEdit32.png")));
		JButton deleteuser = new JButton("Actif/Inactif", new ImageIcon(this.getClass().getResource("UserDelete32.png")));
		JButton importelement = new JButton("Importer", new ImageIcon(this.getClass().getResource("CardboardBoxDownload32.png")));
		JButton exportelement = new JButton("Exporter", new ImageIcon(this.getClass().getResource("CardboardBoxUpload32.png"))); 
		
		nouveautournois.setBorder(new BevelBorder(BevelBorder.RAISED));
		coBD.setBorder(new BevelBorder(BevelBorder.RAISED));
		userAdd.setBorder(new BevelBorder(BevelBorder.RAISED));
		refresh.setBorder(new BevelBorder(BevelBorder.RAISED));
		//editUser.setBorder(new BevelBorder(BevelBorder.RAISED));
		deleteuser.setBorder(new BevelBorder(BevelBorder.RAISED));
		importelement.setBorder(new BevelBorder(BevelBorder.RAISED));
		exportelement.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		this.setOrientation(1);
		this.setOpaque(true);
		
		this.add(nouveautournois);
//		this.add(coBD);
		this.addSeparator();
		this.add(userAdd);
		this.add(deleteuser);
		//this.add(editUser);
		this.addSeparator();
		this.add(refresh);
		this.addSeparator();
		this.add(importelement);
		this.add(exportelement);
		
		this.setLayout(new GridLayout(11, 1));
		
		this.setVisible(true);
		
		//Controleur
		coBD.addActionListener(new ControleurDB());
		refresh.addActionListener(new ControleurRefresh(MainWindows.getListejoueur()));
		userAdd.addActionListener(new ControleurAddJoueur());
		nouveautournois.addActionListener(new ControleurOpenfenetre("tournois"));
		exportelement.addActionListener(new ControleurExport());
		deleteuser.addActionListener(new ControleurSupprimerJoueur());
		importelement.addActionListener(new ControleurImport());
		//
	}
	
	
}
