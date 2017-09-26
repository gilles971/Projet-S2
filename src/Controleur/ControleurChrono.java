package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import IHM.MainWindows;
import Main.Chrono;

public class ControleurChrono implements ActionListener {

	private String texte;
	private Chrono chrono;
	private JTextField temps;
	private JButton pause;
	
	
	public ControleurChrono(Chrono chrono, String texte, JTextField temps, JButton pause) {
		this.chrono = chrono;
		this.texte = texte;
		this.temps = temps;
		this.pause = pause;
	}


	public void actionPerformed(ActionEvent e) {
		if(texte == "d"){
			if(this.pause.getText() == "Arreter"){
				MainWindows.chrono2.stop();
				MainWindows.chrono2.setTempsRestant(1);
				MainWindows.chrono2.getTimer().restart();
				MainWindows.chrono2.start();
				this.pause.setText("Démarrer");
			}
			else if(this.pause.getText() == "Démarrer"){
				this.pause.setText("Arreter");
				MainWindows.chrono2.getTimer().restart();
				MainWindows.chrono2.setTempsRestant(Integer.parseInt(this.temps.getText())*60);
				MainWindows.chrono2.start();
			}
		}
		
		if(texte == "p"){
			if(this.pause.getText() == "Pause"){
				MainWindows.chrono2.stop();
				this.pause.setText("Reprendre");
			}
			else if(this.pause.getText() == "Reprendre"){
				MainWindows.chrono2.start();
				this.pause.setText("Pause");
			}
		}
		
	}
	
	

}
