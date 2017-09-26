package Table;
import java.awt.Graphics;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import Main.Tournois;
import Main.database;


public class TableJoueur extends JTable{

	
	public static String[] titre = {"Classement", "Nom", "Prénom", "Âge", "Sexe", "Niveau", "Expérience", "Score", "Nb match joués"};
	static Object[][] donnees;
	
	
	public static Object[][] getDonneesJoueur(String actif) throws Exception {
		
		int actif2 =0;
		
		if(actif == "Actif"){
			actif2 = 1;
		}
		else{
			actif2 = 0;
		}
		
		
		if(Tournois.getNom() != null){
		
		if(actif == "Actif" || actif == "Inactif"){
			
			ResultSet nombre = database.select("select count(*) from joueur where actif= "+actif2+"");
			int nombre1 = 0;
			while (nombre.next()){
				nombre1 = nombre.getInt("");				
			}
			
			if(nombre1==0){
				donnees = new Object[1][9];
			}
			else{donnees = new Object[nombre1][9];}
			
			
		ResultSet resultat = database.select("select * from joueur where actif = "+actif2+" order by score desc");
		int i = 0;
		int z = 1;
		while (resultat.next()){
			int y = 0;
			new JPanel();
			TableJoueur.donnees[i][y++] = Integer.toString(z++);
			TableJoueur.donnees[i][y++] = resultat.getString("Nom");
			TableJoueur.donnees[i][y++] = resultat.getString("Prenom");
			TableJoueur.donnees[i][y++] = resultat.getString("Age");
			TableJoueur.donnees[i][y++] = resultat.getString("Sexe");
			TableJoueur.donnees[i][y++] = resultat.getString("Niveau");
			TableJoueur.donnees[i][y++] = resultat.getString("Experience");
			TableJoueur.donnees[i][y++] = resultat.getString("Score");
			TableJoueur.donnees[i][y++] = resultat.getString("NBMATCHJOUE");			
			i++;
		}
		return TableJoueur.donnees;
		}
		else if(actif == "Tous"){
			
			ResultSet nombre = database.select("select count(*) from joueur");
			int nombre1 = 0;
			while (nombre.next()){
				nombre1 = nombre.getInt("");				
			}
			
			if(nombre1==0){
				donnees = new Object[1][9];
			}
			else{donnees = new Object[nombre1][9];}
			
			
			ResultSet resultat = database.select("select * from joueur order by score desc");
			int ii = 0;
			int zz = 1;
			while (resultat.next()){
				int yy = 0;
				TableJoueur.donnees[ii][yy++] = Integer.toString(zz++);
				TableJoueur.donnees[ii][yy++] = resultat.getString("Nom");
				TableJoueur.donnees[ii][yy++] = resultat.getString("Prenom");
				TableJoueur.donnees[ii][yy++] = resultat.getString("Age");
				TableJoueur.donnees[ii][yy++] = resultat.getString("Sexe");
				TableJoueur.donnees[ii][yy++] = resultat.getString("Niveau");
				TableJoueur.donnees[ii][yy++] = resultat.getString("Experience");
				TableJoueur.donnees[ii][yy++] = resultat.getString("Score");
				TableJoueur.donnees[ii][yy++] = resultat.getString("NBMATCHJOUE");			
				ii++;
			}
			return TableJoueur.donnees;
			}
		}
		
		else{
		return donnees = new Object[1][9]; 
		}

		return donnees= new Object[1][9];
}





}



	
