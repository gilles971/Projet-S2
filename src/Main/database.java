package Main;

import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.util.SqlTool;

import com.opencsv.CSVReader;

import IHM.IHMExport;




//import au.com.bytecode.opencsv.CSVWriter;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//import org.apache.commons.lang3.StringUtils;
public class database {

	/**
	 * @param args
	 * @throws SQLException
	 */

	static Connection conn;

	public static boolean SessionOracle(String user, String passwd,
			String serv, String hote, int port, String SID) {
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			String connex = "jdbc:" + serv + ":thin:@" + hote + ":" + port
					+ ":" + SID;
			conn = DriverManager.getConnection(connex, user, passwd);
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean Connexiondb() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		try {
			String tmp = "jdbc:hsqldb:file:db/" + Tournois.getNom();
			conn = DriverManager.getConnection(tmp, "sa", "");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean Connexiontournois() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:file:db/tournois",
					"sa", "");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static void shutdown() throws Exception {
		Statement st = conn.createStatement();
		st.execute("SHUTDOWN COMPACT");
		conn.close();
	}

	public static Connection getConnetion() {
		return conn;
	}

	// Pour select normal
	public static ResultSet select(String requete) throws Exception {
		Connexiondb();
		Statement stmt = conn.createStatement();
		ResultSet resultat = stmt.executeQuery(requete);
		shutdown();
		return resultat;
	}

	// Pour select tournoi
	public static ResultSet selecttournois(String requete) throws Exception {
		Connexiontournois();
		Statement stmt = conn.createStatement();
		ResultSet resultat = stmt.executeQuery(requete);
		shutdown();
		return resultat;
	}

	// Pour Insérer
	public static void insert(String requete) throws Exception {
		Connexiondb();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(requete);
		shutdown();
	}

	// Pour mettre à jour
	public static void update(String requete) throws Exception {
		Connexiondb();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(requete);
		shutdown();
	}

	// Pour mettre à jour tournois
	public static void updatetournois(String requete) throws Exception {
		Connexiontournois();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(requete);
		shutdown();
	}

	public static void tabletournois() throws Exception {
		updatetournois("create table tournois(nom varchar(255) not null, nbterrain int not null, numtour int not null)");
	}

	// Pour supprimer
	public static void delete(String requete) throws Exception {
		Connexiondb();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(requete);
		shutdown();
	}

	public static void table() throws Exception {
		Connexiondb();
		update("create table joueur (idjoueur int IDENTITY primary key, nom varchar(255) not null, prenom varchar(255) not null, age int not null, sexe varchar(255) not null, experience varchar(255) not null, niveau varchar(255) not null, score int, Nbmatchjoue int, actif int)");
		update("create table equipe(idequipe int IDENTITY primary key, idjoueur1 int not null, idjoueur2 int not null, numerotour int not null)");
		update("create table match(idmatch int IDENTITY primary key, numtour int not null, terrain int not null, idequipe1 int not null, idequipe2 int not null, pointequipe1 int not null, pointequipe2 int not null, matchjoue int not null)");
	}							

	public static void premier() throws Exception {
		try {
			selecttournois("select * from tournois");
		} catch (Exception e) {
			Connexiontournois();
			tabletournois();
		}

	}

	// export
	public static void export(IHMExport IHM) throws IOException {
		// ----------------------Choisir le chemin------------------
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Fichier CSV", "csv");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(IHM);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getName());
		}

		// creation dossier au nom du tournois

		// Détermination des slash différent de windows et unix
		String slash = "";
		if (System.getProperty("os.name").equals(new String("Windows"))) {
			slash = "\\";
		} else {
			slash = "/";
		}
		;

		String cheminDir = chooser.getCurrentDirectory() + slash
				+ chooser.getName(chooser.getSelectedFile()) + slash
				+ "export-" + Tournois.getNom();
		System.out.println(cheminDir);
		File dir = new File(cheminDir);
		dir.mkdirs();
		// export table joueur
		try {
			String cheminJ = cheminDir + slash + "joueur.csv";
			System.out.println(cheminJ);
			BufferedWriter outJoueur = new BufferedWriter(new FileWriter(
					cheminJ));
			com.opencsv.CSVWriter writer = new com.opencsv.CSVWriter(outJoueur);

			ResultSet exp_joueur;
			String[] values = { "idjoueur", "nom", "prenom", "age", "sexe",
					"experience", "niveau", "score", "Nombre de match joué" };
			writer.writeNext(values);
			try {
				exp_joueur = database.select("select * from joueur");
				while (exp_joueur.next()) {
					String id = exp_joueur.getString("idjoueur");
					String nom = exp_joueur.getString("nom");
					String prenom = exp_joueur.getString("prenom");
					String age = exp_joueur.getString("age");
					String sexe = exp_joueur.getString("sexe");
					String expe = exp_joueur.getString("experience");
					String niv = exp_joueur.getString("niveau");
					String score = exp_joueur.getString("score");
					String nbmatch = exp_joueur.getString("Nbmatchjoue");
					values = new String[] { id, nom, prenom, age, sexe, expe,
							niv, score, nbmatch };
					writer.writeNext(values);
				}
				System.out.println("Export Joueur ok!");
				outJoueur.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String cheminM = cheminDir + slash + "match.csv";
			BufferedWriter outMatch = new BufferedWriter(
					new FileWriter(cheminM));
			writer = new com.opencsv.CSVWriter(outMatch);

			ResultSet exp_match;
			values = new String[] { "Numéro du match", "tour", "terrain",
					"N° Paire 1", "N° Paire 2", "Score Paire 1",
					"Score Paire 2" };
			writer.writeNext(values);
			try {
				exp_match = database.select("select * from match");
				while (exp_match.next()) {
					String id = exp_match.getString("idmatch");
					String numtour = exp_match.getString("numtour");
					String terrain = exp_match.getString("terrain");
					String idequipe1 = exp_match.getString("idequipe1");
					String idequipe2 = exp_match.getString("idequipe2");
					String pointequipe1 = exp_match.getString("pointequipe1");
					String pointequipe2 = exp_match.getString("pointequipe2");

					values = new String[] { id, numtour, terrain, idequipe1,
							idequipe2, pointequipe1, pointequipe2 };
					writer.writeNext(values);
				}
				System.out.println("Export Match ok!");
				outMatch.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String cheminEquipe = cheminDir + slash + "paire.csv";
			BufferedWriter outEquipe = new BufferedWriter(new FileWriter(
					cheminEquipe));
			writer = new com.opencsv.CSVWriter(outEquipe);

			ResultSet exp_equipe;
			values = new String[] { "Id du paire", "Id du joueur 1",
					"Id du joueur 2", "Numéro du tour" };
			writer.writeNext(values);
			try {
				exp_equipe = database.select("select * from equipe");
				while (exp_equipe.next()) {
					String idpaire = exp_equipe.getString("idequipe");
					String idj1 = exp_equipe.getString("idjoueur1");
					String idj2 = exp_equipe.getString("idjoueur2");
					String numtour = exp_equipe.getString("numerotour");

					values = new String[] { idpaire, idj1, idj2, numtour };
					writer.writeNext(values);
				}
				System.out.println("Export Equipe ok!");
				outEquipe.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally {
				System.out.println("export ok!");
			}
		} finally {
			System.out.println("export ok!");
		}
	}
	
	public static void importt(IHMExport IHM){
		// ----------------------Choisir le chemin------------------
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier CSV", "csv");
				chooser.setFileFilter(filter);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(IHM);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ chooser.getSelectedFile().getName());
				}

				// creation dossier au nom du tournois

				// Détermination des slash différent de windows et unix
				String slash = "";
				if (System.getProperty("os.name").equals(new String("Windows"))) {
					slash = "\\";
				} else {
					slash = "/";
				}
				;
				
				//------------Import Joueur----------------
				try {
					String cheminJ = chooser.getCurrentDirectory() + slash
							+ chooser.getName(chooser.getSelectedFile())+slash+"joueur.csv";
					CSVReader csvReader = new CSVReader(new FileReader(cheminJ));
					String[] row = csvReader.readNext();
					while((row = csvReader.readNext()) != null) {
						//System.out.println(row[1]+","+ row[2]+"," /* + row[3]*/); //+","+row[4]+","+row[5]+","+row[6]+","+row[7]+","+ (String) row[8]);
					    database.insert("insert into joueur(nom, prenom, age, sexe, experience, niveau, score, Nbmatchjoue,actif) values('"+row[1]+"','"+ row[2]+"'," + row[3]+",'"+row[4]+"','"+row[5]+"','"+row[6]+"','"+row[7]+"','"+row[8]+"',1)");
					}
					csvReader.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				//------------Import paire----------------
				try {
					String cheminP = chooser.getCurrentDirectory() + slash
							+ chooser.getName(chooser.getSelectedFile())+slash+"paire.csv";
					CSVReader csvReader = new CSVReader(new FileReader(cheminP));
					String[] row = csvReader.readNext();
					while((row = csvReader.readNext()) != null) {
					    database.insert("insert into equipe(idjoueur1, idjoueur2,numerotour) values("+
					       row[1]+","+ row[2]+"," + row[3]+")");
					}
					csvReader.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				//------------Import match----------------
				try {
					String cheminM = chooser.getCurrentDirectory() + slash
							+ chooser.getName(chooser.getSelectedFile())+slash+"match.csv";
					CSVReader csvReader = new CSVReader(new FileReader(cheminM));
					String[] row = csvReader.readNext();
					while((row = csvReader.readNext()) != null) {
					    database.insert("insert into match(numtour,terrain,idequipe1,idequipe2,pointequipe1,pointequipe2,matchjoue) values("+
					       row[1]+","+ row[2]+"," + row[3]+","+row[4]+","+row[5]+","+row[6]+",1)");
					}
					csvReader.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	}
}
