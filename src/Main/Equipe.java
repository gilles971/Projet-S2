package Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 *
 * @author DAHMANI Sélim
 *
 */

/* Classe equipe */


public class Equipe {
private int idEquipe;
private int j1;
private int j2;
private int tour;
private boolean joue;

	/*Constructeur de la classe Equipe*/
	public Equipe(int j1, int j2, int idEquipe, int tour){
		this.j1 = j1;
		this.j2 = j2;
		this.idEquipe=idEquipe;
		this.tour = tour;
		this.joue = false;
		}
	
	
	

		
	/*méthode get j1*/
	public int getJoueur1(){
		return this.j1;
		}
	
	/*méthode get j2*/
	public int getJoueur2(){
		return this.j2;
		}

	/*méthode get identifiant equipe*/
	public int getIdEquipe(){
		return this.idEquipe;
		}	
	
	/*méthode set j1*/
	public void setJoueur1(int j1){
		this.j1 = j1;
		}
	
	/*méthode set j2*/
	public void setJoueur2(int j2){
		this.j2 = j2;
	}

	public boolean getJoue(){
		return this.joue;
	}
	
	public void setJoue(boolean joue){
		this.joue = joue;
	}
	
	/*méthode set identifiant equipe*/
	public void setIdEquipe(int idEquipe){
		this.idEquipe = idEquipe;
		}

	/*toString()*/
	public String toString(){
		return (this.j1 + " " + this.j2);
	}
	
	public void setTour(int tour){
			this.tour = tour;
	}
	
	public int getTour(){
			return this.tour;
	}

	public static List getListPaire() throws Exception{
		List paire = new ArrayList();
		try {
			ResultSet duo = database.select("select * from equipe");
			while (duo.next()){
				int idpaire = duo.getInt("idequipe");
				int idj1 = duo.getInt("idjoueur1");
				int idj2 = duo.getInt("idjoueur2");
				int numtour = duo.getInt("numerotour");
				paire.add(new Equipe(idj1, idj2, idpaire, numtour));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erreur dans la base de donnée: " + e.getMessage());
		}
		return paire;
	}
		
	public static boolean alreadyTeam(List<Equipe> ListPaire, Joueur j1, Joueur j2) throws Exception{
		String nb_team = "";
		int j1Id = j1.getIdJoueur();
		int j2Id = j2.getIdJoueur();
		Iterator<Equipe> i = ListPaire.iterator();
		boolean alrdy = false;
		while(i.hasNext() && !alrdy){
			Equipe paireAnalys = i.next();
			if(((paireAnalys.getJoueur1() == j1.getId()) && (paireAnalys.getJoueur2() == j2.getId()))||((paireAnalys.getJoueur1() == j2.getId()) && (paireAnalys.getJoueur2() == j1.getId()))){
				alrdy = true;
			}
		}
		return alrdy;
		
	}



}

