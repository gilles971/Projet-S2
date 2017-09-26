package Main;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Match {
	private String idMatch;
	// terrain a voir si on peut pas le mettre sous forme de tableau intégré dans la classe Tour
	private char terrain;

	private Equipe equipe1;
	private int scoree1;
	private Equipe equipe2;
	private int scoree2;
	private Equipe vainqueur; /* Attribution des points aux joueurs de l'équipe qui emporte le match*/

	public Match(String idMatch, char terrain, Equipe e1, Equipe e2 /* ,Joueur vainqueur  => le vainqueur n'est pas encore défini et ce n'est pas un seul joueur mais une équipe*/) throws Exception{

	this.idMatch = idMatch;
	this.equipe1 = e1;
	this.equipe2 = e2;
	this.terrain = terrain;
	this.setScoree1(0);
	this.setScoree2(0);
	/*this.vainqueur = vainqueur;*/

	}
	
	
	public static int nbMatchParTerrain(int numtour, int terrain) throws Exception{
		ResultSet resultat = database.select("select count(*) from match where numtour ="+numtour+" and terrain = "+terrain+"");
		int nombre = 0;
		while (resultat.next()){
			nombre = resultat.getInt("");
		}
		return nombre;
	}
	
	public static int nbmatch() throws Exception{
		ResultSet resultat = database.select("select count(*) from match");
		int nombre = 0;
		while(resultat.next()){
			nombre = resultat.getInt("");
		}
		return nombre;
	}
	
	public void recmatch(int numterrain, int numtour) throws Exception{
		ResultSet resultat = database.select("select ");
		
	}
	
	
	
	


	public String getIdMatch(){
		return this.idMatch;
	}

	public char getTerrain(){
		return this.terrain;
	}
	
	public void setEquipeVainqueur(Equipe team){
		this.vainqueur = team;
	}
	public Equipe getEquipeVainqueur(){
		return this.vainqueur;
	}
	


	public Equipe getEquipe1(){
		return this.equipe1;
	}

	public Equipe getEquipe2(){
		return this.equipe1;
	}



	public int getScoree2() {
		return scoree2;
	}



	public void setScoree2(int scoree2) throws Exception {
		this.scoree2 = scoree2;
		database.update("update match set scoree2 ='"+scoree2+"'where idequipe2 ="+this.equipe2.getIdEquipe());
	}



	public int getScoree1() {
		return scoree1;
	}



	public void setScoree1(int scoree1) {
		this.scoree1 = scoree1;
	}

}

