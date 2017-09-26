package Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Tournois {
	private int nbJoueurs;
	private int nbTours;
	private int nbTerrain;
	private static int nbJoueursAncien;
	private static int nbJoueursNouveau;
	private static int nbToursEffectif;
	private static String nom;






		public Tournois(String nom, int nbJoueurs, int nbTours, int nbTerrain) {
		 this.nom = nom;
		 this.nbJoueurs = nbJoueurs;
		 this.nbTours = nbTours;
		 this.setNbToursEffectif(0);
		}
		
	
		public void addTournois(String nom) throws Exception{
			database.insert("insert into tournois values('"+nom+"')");
		}
		
		public static String[] listeTournois() throws Exception{
			String[] liste = new String[nbtournois()];
			ResultSet resultat = database.selecttournois("select * from tournois");
			int i = 0;
			if(resultat == null){
				return liste;
			}
			else {
			while (resultat.next()){
				liste[i] = resultat.getString("nom");
				i++;
			}
			return liste;
			}
		}
		
		public static int nbtournois() throws Exception{
			int nombre = 0;
			ResultSet resultat = database.selecttournois("select count(*) from tournois");
			while (resultat.next()){
				nombre = resultat.getInt("");
			}
			return nombre;
		}



		public int getNbJoueurs(){
			return this.nbJoueurs;
		}


		public void addTours(){
			this.setNbToursEffectif(this.getNbToursEffectif() + 1);
		}

		public int getTours(){
			return this.getNbToursEffectif();
		}

		public void setNbTours(int nbTours){
			this.nbTours = nbTours;
		}



		public int getNbTours(){
			return this.nbTours;
		}

		public static int getNbTerrain() throws Exception{
			ResultSet resultat = database.selecttournois("select nbterrain from tournois where nom = '"+Tournois.getNom()+"'");
			int nombre = 0;
			while(resultat.next()){
				nombre = resultat.getInt("nbterrain");
			}
			return nombre;
		}

		
		public void setNbJoueurs() throws Exception
		{
			ResultSet nombre = database.select("select count(*) from Joueur");
			int nombre1 = 0;
			while (nombre.next()){
				nombre1 = nombre.getInt("");				
			}
			this.nbJoueurs = nombre1;
		}
		
		
		

		public static void setNbJoueursAncien() throws Exception

		{
			ResultSet nombre = database.select("select count(*) from joueur where experience = 'Ancien' and actif = 1");
			int nombre1 = 0;
			while (nombre.next()){
				nombre1 = nombre.getInt("");				
			}

		nbJoueursAncien = nombre1;
		}
		
		public void setNombreJoueurs() throws Exception{
		
			String nb1 = "";
			ResultSet nb = database.select("select count (*) from joueur");
			while (nb.next())
			 {
				nb1 = nb.getString("count(*)");
			 }
			int nombre_joueur = Integer.parseInt(nb1);
			this.nbJoueurs = nombre_joueur;
			}
		


		public static void setNbJoueursNouveau() throws Exception

		{
			ResultSet nombre = database.select("select count(*) from joueur where experience = 'Nouveau' and actif = 1");
			String nombre1 = null;
			while (nombre.next()){
				nombre1 = nombre.getString("");				
			}

			nbJoueursNouveau = Integer.parseInt(nombre1);
		}
		
		public static int nbEquipesMix() throws Exception{
			setNbJoueursAncien();
			setNbJoueursNouveau();
			int abs = Math.abs(nbJoueursAncien - nbJoueursAncien);
			int nb;
		if (nbJoueursAncien > nbJoueursNouveau)
		{
			return nbJoueursNouveau;

		}
		else
		{

			return nbJoueursAncien;
		}

		}
		
		public int nbEquipes() throws Exception{
			int nb = nbEquipesMix() * 2; //4
			int reste = this.nbJoueurs - nb; //3
			if (reste % 2 == 0)
			{
				return (reste / 2);
			}
			else {return ((reste -1)/2);} //1
		}
		
		public int joueursRestants() throws Exception{
			int nb = nbEquipesMix() * 2;
			int reste = this.nbJoueurs - nb; 
			return (reste % 2);
		}
		
		

		public void setNbTerrains(int nbTerrain)
		{
			this.nbTerrain = nbTerrain;
		}

		
		public static int getNombrepaires() throws Exception{
			ResultSet resultat = database.select("select Count(*) from equipe");
			resultat.next();
			int nbJoueurs = resultat.getInt("");
			return nbJoueurs;
		}
		


		public static int getNbJoueursAncien() {

			return nbJoueursAncien;
		}

		public static int getNbJoueursNouveau() {
			return nbJoueursNouveau;
		}
	

		public static String getNom() {
			return Tournois.nom;
		}


		public static void setNom(String nom) {
			Tournois.nom = nom;
		}


		public static int getNbToursEffectif() {
			return Tournois.nbToursEffectif;
		}


		public static void setNbToursEffectif(int nbToursEffectif) {
			Tournois.nbToursEffectif = nbToursEffectif;
		}
}
