package Controleur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import IHM.MainWindows;
import Main.database;
import Table.TableJoueur;


public class ControleurDB2 implements ActionListener {

	private JLabel co;
	private JTable equipe, joueur;
	private JTextField user;
	private JTextField pass;
	private JTextField server;
	private JTextField host;
	private JTextField port;
	private JTextField sid;
	private JLabel label;
	
	
	public ControleurDB2(JLabel co, JTable joueur, JTable equipe, JTextField user, JTextField pass,
			JTextField server, JTextField host, JTextField port, JTextField sid, JLabel label){
		this.co = co;
		this.joueur = joueur;
		this.equipe = equipe;
		this.user = user;
		this.pass = pass;
		this.server = server;
		this.host = host;
		this.port = port;
		this.sid = sid;
		this.label = label;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
				boolean reussi = database.SessionOracle(user.getText(), pass.getText(),
				server.getText(), host.getText(), Integer.parseInt(port.getText()), sid.getText());
				if (reussi == false){
				label.setText("Connexion �chou�e");
				label.setForeground(Color.RED);
				}
				else {
					label.setText("Connexion R�ussite");
					label.setForeground(Color.GREEN);
					co.setText("Connect�");
					co.setForeground(Color.GREEN);
					try {
						this.joueur = new JTable(TableJoueur.getDonneesJoueur((String)MainWindows.getListejoueuractif().getSelectedItem()), TableJoueur.titre);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
	}


