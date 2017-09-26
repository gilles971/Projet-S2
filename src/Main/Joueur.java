package Main;
import java.sql.*;
import java.util.*;


	

public class Joueur implements Comparable{

	private int idjoueur;
	private int age;
	private String sexe;
	private int score;
	private int nbTour;

	private static int nombreJoueurs;

	private boolean joue;

	protected boolean nouveau;
	private String niveau;
	private String nom;
	private String prenom;
	private static List ListeAncien;
	private static List ListeNouveau;

	public Joueur(int idjoueur, String nom, String prenom, int age, String sexe, boolean nouveau, String niveau, int nbtour) throws SQLException 
			{
			this.idjoueur = idjoueur;
			this.age = age;
			this.sexe = sexe;
			this.nouveau = nouveau;
			this.niveau = niveau;
			this.score = 0;
			this.joue = false;
			this.nom = nom;
			this.prenom = prenom;
			this.nbTour = nbtour;
			}

	public int getIdJoueur(){
			return this.idjoueur;
	}
	
	public static List getListeAncien() throws Exception{
		List ListAnc = new ArrayList();
		try {
			ResultSet anciens = database.select("select * from joueur where experience = 'Ancien' and actif = 1");
			while(anciens.next()){
				int idjoueur = anciens.getInt("idjoueur");
				String nom = anciens.getString("nom");
				String prenom = anciens.getString("prenom");
				int age = anciens.getInt("age");
				String sexe = anciens.getString("sexe");
				String niveau = anciens.getString("experience");
				String score = anciens.getString("score");
				int nbmatch = anciens.getInt("Nbmatchjoue");
				ListAnc.add(new Joueur(idjoueur, nom, prenom, age, sexe, false, niveau, nbmatch));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur dans la base de donn�e." + e.getMessage());
		}
		
		return ListAnc; 
	}
	
	public static List getListeNouveau() throws Exception{
		List ListNouv = new ArrayList();
		try {
			ResultSet nouveau = database.select("select * from joueur where experience = 'Nouveau' and actif = 1");
			while(nouveau.next()){
				int idjoueur = nouveau.getInt("idjoueur");
				String nom = nouveau.getString("nom");
				String prenom = nouveau.getString("prenom");
				int age = nouveau.getInt("age");
				String sexe = nouveau.getString("sexe");
				String niveau = nouveau.getString("experience");
				String score = nouveau.getString("score");
				int nbmatch = nouveau.getInt("Nbmatchjoue");
				ListNouv.add(new Joueur(idjoueur, nom,prenom, age, sexe, false, niveau, nbmatch));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur dans la base de donn�e." + e.getMessage());
		}
		
		return ListNouv; 
}
	
	public static void creationListe(){
			ListeAncien = new ArrayList();
			ListeNouveau = new ArrayList();
	}
	
	public void setJoue(boolean joue){
	this.joue=joue;
	}

	public boolean getJoue(){
	return this.joue;
	}

	public static void resetJoue(){
		for(int i = 0; i< ListeAncien.size(); i++){
			((Joueur)ListeAncien.get(i)).joue = false;
		}
		for(int i = 0; i < ListeNouveau.size(); i++){
			((Joueur)ListeNouveau.get(i)).joue = false;
		}
	}
	
	public int getId(){
		return this.idjoueur;
	}
	
	public void setAge(int age) throws Exception
		{
		database.update("update joueur set age = '"+ age+ "' where idjoueur="+ this.idjoueur);
		this.age = age;
		}	
	
	public int getAge()
		{
		return this.age;
		}

	public void setSexe(String sexe) throws Exception
		{
		database.update("update joueur set sexe = '"+sexe+ "' where idjoueur="+ this.idjoueur);
		this.sexe = sexe;
		}	
	
	public String getNom(){
			return this.nom;
	}
	public String getSexe()
		{
		return this.sexe;
		}
	
	public void setScore(int score) throws Exception
		{
		database.update("update joueur set score = "+score+ " where idjoueur="+ this.idjoueur);
		this.score = score;
		}	
	

	public void setNiveau(int lvl) throws Exception{
			switch(lvl){
			case 0: 
				database.update("update joueur set experience = 'Nul' where idjoueur="+ this.idjoueur);
				break;
			case 1: 
				database.update("update joueur set experience = 'Intermediaire' where idjoueur="+ this.idjoueur);
				break;
			case 2:
				database.update("update joueur set experience = 'Expert' where idjoueur="+ this.idjoueur);
				break;
			}
	}

	public void setNiveau(String lvl) throws Exception{
				database.update("update joueur set experience = '"+lvl+"' where idjoueur="+ this.idjoueur);

			this.niveau = lvl;
}
	
	public int getScore()
		{
		return this.score;
		}



	public boolean nouveau(){
	return nouveau;
	}



	public static void setNombreJoueurs() throws Exception{

		
		String nb1 = "";
		ResultSet nb = database.select("select count (*) from joueur");
		while (nb.next())
		 {
			nb1 = nb.getString("count(*)");
		 }
		int nombre_joueur = Integer.parseInt(nb1);
		Joueur.nombreJoueurs = nombre_joueur;
		}
	
	public String getPrenom(){
		return this.prenom;
	}
	
	
	
	public static boolean AjouterJoueur(String nom, String prenom, String sexe, int age, String experience, String niveau) throws SQLException{
		/*Intégration dans la base de donnée */
	try {
		database.insert("insert into joueur(nom, prenom, age, sexe, experience, niveau, score, nbmatchjoue, actif ) values ('"+nom+"' , '"+prenom+"', "+age+" , '"+sexe+"', '"+experience+"', '"+ niveau + "', 0, 0, 1)");
		return true;
		
	}
	catch (Exception e){e.printStackTrace(); return false;}
	}
	
	public static int getNombreJoueurs(){
	 	return Joueur.nombreJoueurs;
	}


	public static void setNombreJoueurs(int nombreJoueurs) {
		Joueur.nombreJoueurs = nombreJoueurs;
	}

	
	public void addTour(){
		ResultSet nbtour_joueur;
		try {
			nbtour_joueur = database.select("select Nbmatchjoue from joueur where idjoueur = "+this.idjoueur);
			int nb = 0;
			while(nbtour_joueur.next()){
				nb = nbtour_joueur.getInt("Nbmatchjoue");
			}
			nb++;
			database.update("update joueur set Nbmatchjoue ="+nb+"where idjoueur = "+this.idjoueur);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur de la base de donnée: " + e.getMessage());
		}
		
	}
	 
	public int getNbTours(){
		return this.nbTour;
	}
	
	

	
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Joueur j2 = (Joueur)o;
		int nbToursJ1 = this.getNbTours();
		int nbToursJ2 = j2.getNbTours();
		if( nbToursJ1 < nbToursJ2){
			return -1;
		} else {
			if(nbToursJ1 > nbToursJ2){
				return 1;
			} else{
				return 0;
			}
		}
	}
	
}






