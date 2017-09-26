package IHM;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.border.EmptyBorder;

import Controleur.ControleurDB2;



public class CoOracle extends JFrame {

	public CoOracle() {
		super("Connexion serveur Oracle");

		try {
			this.setIconImage(ImageIO.read(new File("png/DatabaseAdd32.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

		final JPanel onglet1 = new JPanel();
		String text = "Connexion";
		JButton but = new JButton(text);
		


		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		final JTextField user = new JTextField();
		final JTextField pass = new JTextField();
		final JTextField server = new JTextField("oracle");
		final JTextField host = new JTextField();
		final JTextField port = new JTextField("1521");
		final JTextField sid = new JTextField();
		JLabel labeluser = new JLabel("Identifiant");
		JLabel labelpass = new JLabel("Mot de passe");
		JLabel labelserver = new JLabel("serveur");
		JLabel labelhost = new JLabel("Hôte");
		JLabel labelport = new JLabel("Port");
		JLabel labelsid = new JLabel("SID");
		

		onglet1.add(labeluser);
		onglet1.add(user);
		onglet1.add(labelpass);
		onglet1.add(pass);
		onglet1.add(labelserver);
		onglet1.add(server);
		onglet1.add(labelhost);
		onglet1.add(host);
		onglet1.add(labelport);
		onglet1.add(port);
		onglet1.add(labelsid);
		onglet1.add(sid);
		onglet1.add(but);
		
	
		
		Dimension dim = new Dimension(100, 20);
		Dimension dimfene = new Dimension (380, 150);
		user.setPreferredSize(dim);
		pass.setPreferredSize(dim);
		server.setPreferredSize(dim);
		host.setPreferredSize(dim);
		port.setPreferredSize(dim);
		sid.setPreferredSize(dim);
		onglet1.setPreferredSize(dimfene);


		final JLabel label1  = new JLabel("");
		
		
		onglet1.add(label1);

		this.setContentPane(onglet1);

		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		onglet1.setLayout(new GridLayout(7, 2));
		this.setVisible(true);
		
		//Controleur
		but.addActionListener(new ControleurDB2(MainWindows.getCo(), MainWindows.getListejoueur() ,MainWindows.getListeequipe(), user,
		pass, server, host, port, sid, label1));
		//
		
		
	}
}