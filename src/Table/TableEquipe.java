package Table;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import Controleur.ControleurModifierEquipe;
import Controleur.ControleurModifierEquipe2;
import IHM.MainWindows;
import Main.Tournois;
import Main.database;


public class TableEquipe extends JTable{

	
	public static String[] titre = {"Id Paire", "Nom joueur 1", "Nom joueur 2", "Tour"};
	public static Object[][] donnees;
	static String[][] joueur;
	static String[] joueursimple;
	private static int nombre2;
	
	static JComboBox listejoueur;
	
	
	public String[][] getJoueur() {
		return this.joueur;
	}


	public void setJoueur(String[][] joueur) {
		this.joueur = joueur;
	}


	public static String[] getJoueursimple() {
		return joueursimple;
	}


	public static void setJoueursimple(String[] joueursimple) {
		TableEquipe.joueursimple = joueursimple;
	}


	public static Object[][] getDonneesEquipe(int numtour) throws Exception{
			
		if(Tournois.getNom() != null){
		ResultSet nombre = database.select("select count(*) from Equipe where numerotour = "+numtour+"");
		int nombre1 = 0;
		while (nombre.next()){
			nombre1 = nombre.getInt("");				
		}
		
		if(nombre1==0){
			donnees = new Object[1][4];
		}
		else{donnees = new Object[nombre1][4];}
		ResultSet resultat3 = database.select("select count(*) from joueur where actif = 1");
		int nombre2 = 0;
		while(resultat3.next()){
			nombre2 = resultat3.getInt(""); 
		}
		
		joueur = new String[nombre2][3];
		joueursimple = new String[nombre2];
		
		ResultSet resultat2 = database.select("select idjoueur, nom, prenom from joueur where actif = 1");
		int ii=0;
		while(resultat2.next()){
			int yy = 0;
			joueur[ii][yy++] = resultat2.getString("nom");
			joueur[ii][yy++] = resultat2.getString("prenom");
			joueur[ii][yy++] = resultat2.getString("idjoueur");
			joueursimple[ii] = joueur[ii][0] + " " + joueur[ii][1];
			ii++;
		}


	
		ResultSet resultat = database.select("select e.idequipe, j1.nom, j1.prenom, j2.nom as nom2, j2.prenom as prenom2, e.numerotour from equipe e, joueur j1, joueur j2 where e.idjoueur1 = j1.idjoueur and e.idjoueur2 = j2.idjoueur and numerotour = "+numtour+" ");
		int i = 0;
		while (resultat.next()){
			int y = 0;
			TableEquipe.donnees[i][y++] = resultat.getString("Idequipe");
			TableEquipe.donnees[i][y++] = resultat.getString("Nom") + " " + resultat.getString("prenom");
			TableEquipe.donnees[i][y++] = resultat.getString("nom2") + " " + resultat.getString("prenom2");
			TableEquipe.donnees[i][y++] = resultat.getString("numerotour");
			i++;
		}
		
		
		return TableEquipe.donnees;
		}
		else{
			return donnees = new Object[1][4]; 
		}
	}
	
	
	public static int idjoueur(int index){
		return Integer.parseInt(TableEquipe.joueur[index][2]);
		
	}


	public static int getNombre2() {
		return nombre2;
	}


	public void setNombre2(int nombre2) {
		this.nombre2 = nombre2;
	}
	
}
