package Table;

import java.sql.ResultSet;

import javax.swing.*;

import Main.Tournois;
import Main.database;

public class TableDeMatch extends JTable {
	
	public static String[] titre = {"Id Match", "Paire 1", "Paire 2", "Score", "Tour"};
	public static String[][] donnees;
	
	
	public static String[][] getDonneesMatch(int numtour) throws Exception{
		
		if(Tournois.getNom() != null){
		ResultSet nombre = database.select("select count(*) from match where numtour = "+numtour+"");
		int nombre1 = 0;
		while (nombre.next()){
			nombre1 = nombre.getInt("");				
		}
		
		if(nombre1==0){
			donnees = new String[1][5];
		}
		else{donnees = new String[nombre1][5];}
		
		
		ResultSet resultat = database.select("select j1.nom, j1.prenom, j2.nom as nom2, j2.prenom as prenom2, j3.nom as nom3, j3.prenom as prenom3, j4.nom as nom4, j4.prenom as prenom4, "
				+ " m.idmatch, m.pointequipe1, m.pointequipe2, m.numtour from joueur j1, joueur j2, joueur j3, joueur j4, equipe e1, equipe e2, match m"
				+ " where m.idequipe1 = e1.idequipe and m.idequipe2 = e2.idequipe and e1.idjoueur1 = j1.idjoueur"
				+ " and e1.idjoueur2 = j2.idjoueur and e2.idjoueur1 = j3.idjoueur and e2.idjoueur2 = j4.idjoueur "
				+ " and numtour = "+numtour+"");
		
		int i = 0;
		while (resultat.next()){
			int y = 0;
			TableDeMatch.donnees[i][y++] = resultat.getString("Idmatch");
			TableDeMatch.donnees[i][y++] = resultat.getString("Nom") + " " + resultat.getString("prenom") +" & "+resultat.getString("nom2") + " " + resultat.getString("prenom2");
			TableDeMatch.donnees[i][y++] = resultat.getString("Nom3") + " " + resultat.getString("prenom3") +" & "+resultat.getString("nom4") + " " + resultat.getString("prenom4");
			TableDeMatch.donnees[i][y++] = resultat.getString("pointequipe1") + " - " + resultat.getString("pointequipe2");
			TableDeMatch.donnees[i][y++] = resultat.getString("numtour");
			i++;
		}
		return TableDeMatch.donnees;
		}
		else{
			return donnees = new String[1][5]; 
		}
	}

}
