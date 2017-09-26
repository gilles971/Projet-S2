package Main;
import java.sql.*;
public class essai {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn ;
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@soracle2:1521:DB1","i1d08b", "i1d08b");
			conn.setAutoCommit(true);
			System.out.println("Connexion OK !") ;
			} catch(SQLException e) {
			conn = null; System.out.println("Erreur de connexion") ;
			}
		
		
		Statement stmt = conn.createStatement();
		ResultSet resultat = stmt.executeQuery("SELECT nom FROM joueur");
		while (resultat.next())
		{
		String nom = resultat.getString("Nom");
	
		System.out.println("Nom : " + nom);
		}
		int nb = stmt.executeUpdate("INSERT INTO joueur VALUES(6, 'gilles', 'F', 28, 'Nouveau', 'Nul') ");
		
		
		
		
		
	}
}
