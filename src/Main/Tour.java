package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JProgressBar;

import IHM.MainWindows;
import Thread.ThreadProgressBar;
import Exception.*;
/*Classe tour */

public class Tour {

	private static int numTour;
	private Match[] tabterrain;
	private static int nombreMatch; // pour un tour
	private int nombreTerrain; // pour définir la taille du tableau

	/* Constructeur de la classe Tour */
	public Tour(int num, int nbTerrain) {
		this.numTour = num;

		this.nombreMatch = 0;
		this.nombreTerrain = nbTerrain;
		tabterrain = new Match[nbTerrain];
	}

	/* get num Tour */
	public int getNumTour() {
		return this.numTour;
	}

	public void incNumTour() {
		this.numTour++;
	}

	public static int nbtour() throws Exception {
		int nombre = 0;
		ResultSet resultat = database
				.selecttournois("select numtour from tournois where nom = '"
						+ Tournois.getNom() + "'");
		while (resultat.next()) {
			nombre = resultat.getInt("numtour");
		}
		return nombre;
	}

	public static String[] listetour() throws Exception {
		int nombre = nbtour();
		String[] liste = new String[nombre];
		int i = 0;
		while (i != nombre) {
			liste[i] = Integer.toString(i + 1);
			i++;
		}
		return liste;
	}

	
	/* set num Tour */
	public void setNumTour(int num) {
		this.numTour = num;
	}

	/* get nombre match */
	public int getNombreMatch() {
		return this.nombreMatch;
	}

	/* set nombre match */
	public void setNombreMatch(int nbr) {
		this.nombreMatch = nbr;
	}

	/* get match sur un terrain */
	// i : indice du tableau//terrain
	public Match getMatch(int i) {
		return tabterrain[i];
	}

	/* set match sur un terrain */
	// i : indice du tableau//terrain
	public Match setMatch(int i) {
		return tabterrain[i];
	}

	
	
	public static void creerEquipe() throws Exception {
		int nbEquipe = Tournois.nbEquipesMix();
		MainWindows.barequipe.setMaximum(nbEquipe);
		MainWindows.barequipe.setMinimum(0);
		MainWindows.barequipe.setValue(0);
		int nbterrain = Tournois.getNbTerrain();
		List<Equipe> paire = Equipe.getListPaire();
		List<Joueur> ancien = Joueur.getListeAncien();
		List<Joueur> nouveau = Joueur.getListeNouveau();
		Collections.sort(ancien);
		Collections.sort(nouveau);
		Joueur j1 = null;
		Joueur j2 = null;
		if ((nbEquipe%2) != 0){
			nbEquipe --;
		}
		for (int i = 0; i < nbEquipe; i++) {
			int k = 0;
			j1 = nouveau.get(i);
			j2 = ancien.get(i);
			while (((j2.getJoue()) || (Equipe.alreadyTeam(paire, j1, j2)))
					&& (k < ancien.size() - 1)) {
				k++;
				j2 = ancien.get(k);
			}
			j1.setJoue(true);
			if (j2.getJoue() == false) {
				j2.setJoue(true);
				database.insert("insert into equipe(idjoueur1, idjoueur2, numerotour) values ("
						+ j1.getIdJoueur()
						+ ","
						+ j2.getIdJoueur()
						+ ","
						+ Tournois.getNbToursEffectif() + ")");
				j1.addTour();
				j2.addTour();
				System.out.println(j1.getPrenom() + " avec " + j2.getPrenom());
				new ThreadProgressBar(MainWindows.barequipe, 1).start();
			} else {
				throw new ExceptionCreerPaire("Plus de combinaison de paire possible");
			}

		}
		if (ancien.size() > nouveau.size()) {
			for (int i = nbEquipe; i < (ancien.size() / 2) + 1; i++) {
				int k = 0;
				int j = 0;
				j1 = ancien.get(k);
				while ((j1.getJoue()) && (k < ancien.size() - 1)) {
					k++;
					j1 = ancien.get(k);
				}
				j1.setJoue(true);
				j2 = ancien.get(j);
				while ((j < ancien.size() - 1)
						&& ((j2.getJoue()) || Equipe.alreadyTeam(paire, j1, j2))) {
					j++;
					j2 = ancien.get(j);
				}
				if (j2.getJoue() == false) {
					j2.setJoue(true);
					if (j1.getIdJoueur() != j2.getIdJoueur()) {

						database.insert("insert into equipe(idjoueur1, idjoueur2, numerotour) values ("
								+ j1.getIdJoueur()
								+ ","
								+ j2.getIdJoueur()
								+ "," + Tournois.getNbToursEffectif() + ")");

						j1.addTour();
						j2.addTour();
						System.out.println(j1.getPrenom() + " indice:" + k
								+ " avec " + j2.getPrenom() + " indice: " + j);
					}
					new ThreadProgressBar(MainWindows.barequipe, 1).start();
				} else {
					throw new ExceptionCreerPaire("Plus de combinaison de paire possible");
				}
			}
		} else if (nouveau.size() > ancien.size()) {
			for (int i = nbEquipe; i < (nouveau.size() / 2) + 1; i++) {
				int k = 0;
				int j = 0;
				while ((j1.getJoue()) && (k < nouveau.size() - 1)) {
					k++;
					j1 = nouveau.get(k);
				}
				j1.setJoue(true);
				j2 = nouveau.get(i);
				while ((j < nouveau.size())
						&& ((j2.getJoue()) || Equipe.alreadyTeam(paire, j1, j2))) {
					j++;
					j2 = nouveau.get(k);
					System.out.println("Etat du joueur 2: " + j2.getJoue());
				}
				if (j2.getJoue() == false) {
					j2.setJoue(true);
					if (j1.getIdJoueur() != j2.getIdJoueur()) {

						database.insert("insert into equipe(idjoueur1, idjoueur2, numerotour) values ("
								+ j1.getIdJoueur()
								+ ","
								+ j2.getIdJoueur()
								+ "," + Tournois.getNbToursEffectif() + ")");

						j1.addTour();
						j2.addTour();
						System.out.println(j1.getPrenom() + " indice:" + k
								+ " avec " + j2.getPrenom() + " indice: " + j);
					}
					new ThreadProgressBar(MainWindows.barequipe, 1).start();
				} else {
					throw new ExceptionCreerPaire("Plus de combinaison de paire possible");
				}
			}
		}
		
	}

	
	public static int getNbEquipe() throws Exception {

		int nb = 0;
		try {
			ResultSet nbEquipe = database
					.select("select count(*) from equipe where numerotour = "
							+ Tournois.getNbToursEffectif() + "");
			while (nbEquipe.next()) {
				nb = nbEquipe.getInt("");
			}
		} catch (SQLException e) {
			System.out.println("Erreur dans la base de donnée: "
					+ e.getMessage());
		}
		return nb;

	}

	public static List<Equipe> getListEquipe() {
		List<Equipe> liste = new ArrayList<Equipe>();
		int id = 0;
		int idJ1 = 0;
		int idJ2 = 0;
		int tour = 0;
		try {
			ResultSet equipe = database
					.select("select * from equipe where numerotour = "
							+ Tournois.getNbToursEffectif() + "");
			while (equipe.next()) {
				id = equipe.getInt("idequipe");
				idJ1 = equipe.getInt("idjoueur1");
				idJ2 = equipe.getInt("idjoueur2");
				liste.add(new Equipe(idJ1, idJ2, id, Tour.numTour));
			}
		} catch (Exception e) {
			
			System.out.println("Erreur dans la basse de donnée: "
					+ e.getMessage());
		}
		return liste;
	}

	
	public static void creer_match() throws Exception {
		int nbequipe = Tour.getNbEquipe();
		MainWindows.barmatch.setMaximum(nbequipe / 2);
		MainWindows.barmatch.setMinimum(0);
		MainWindows.barmatch.setValue(0);
		int nbterrain = Tournois.getNbTerrain(); // No�
		List<Equipe> liste_equipe = Tour.getListEquipe();

		int nbMatch = nbequipe / 2;
		int nbEquipe = Tour.getNbEquipe();

		

		

		for (int i = 1; i <= nbMatch; i++) {
			Equipe e1 = liste_equipe.get((int) (Math.random() * (nbEquipe)));
			Equipe e2 = liste_equipe.get((int) (Math.random() * (nbEquipe)));
			while (e1.getJoue()) {
				e1 = liste_equipe.get((int) (Math.random() * (nbEquipe)));
			}
			while (e2.getJoue() || (e2.equals(e1))) {
				e2 = liste_equipe.get((int) (Math.random() * (nbEquipe)));
			}
	
			e1.setJoue(true);
			e2.setJoue(true);
			database.insert("insert into match(numtour, terrain, idequipe1, idequipe2, pointequipe1, pointequipe2, matchjoue) values ("
					+ Tournois.getNbToursEffectif()
					+ ", "
					+ (((i-1) % nbterrain)+1)
					+ ", "
					+ e1.getIdEquipe()
					+ " , "
					+ e2.getIdEquipe()
					+ ", 0, 0, 0)");
			new ThreadProgressBar(MainWindows.barmatch, 0).start();
	
			
		}
	}
}
