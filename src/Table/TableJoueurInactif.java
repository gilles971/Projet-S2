package Table;
import java.awt.Graphics;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import Main.Tournois;
import Main.database;


public class TableJoueurInactif extends JTable{

	
	public static String[] titre = {"Classement", "Nom", "Prénom", "Âge", "Sexe", "Niveau", "Expérience", "Score", "Nb match joués"};
	static Object[][] donnees;
	
	
	public static Object[][] getDonneesJoueurInactif() throws Exception{
		
		if(Tournois.getNom() != null){
		ResultSet nombre = database.select("select count(*) from joueur where actif=0");
		int nombre1 = 0;
		while (nombre.next()){
			nombre1 = nombre.getInt("");				
		}
		
		if(nombre1==0){
			donnees = new Object[1][9];
		}
		else{donnees = new Object[nombre1][9];}
		
			
		ResultSet resultat = database.select("select * from joueur where actif = 0 order by score desc");
		int i = 0;
		int z = 1;
		while (resultat.next()){
			int y = 0;
			new JPanel();
			TableJoueurInactif.donnees[i][y++] = Integer.toString(z++);
			TableJoueurInactif.donnees[i][y++] = resultat.getString("Nom");
			TableJoueurInactif.donnees[i][y++] = resultat.getString("Prenom");
			TableJoueurInactif.donnees[i][y++] = resultat.getString("Age");
			TableJoueurInactif.donnees[i][y++] = resultat.getString("Sexe");
			TableJoueurInactif.donnees[i][y++] = resultat.getString("Niveau");
			TableJoueurInactif.donnees[i][y++] = resultat.getString("Experience");
			TableJoueurInactif.donnees[i][y++] = resultat.getString("Score");
			TableJoueurInactif.donnees[i][y++] = resultat.getString("NBMATCHJOUE");			
			i++;
		}
		return TableJoueurInactif.donnees;
		}
		else{
			return donnees = new Object[1][9]; 
		}
	}
	
}
	
	
