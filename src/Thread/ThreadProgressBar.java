package Thread;

import java.awt.event.ActionEvent;

import javax.swing.JProgressBar;

import IHM.MainWindows;
import Main.Tour;

public class ThreadProgressBar extends Thread {
	
	private JProgressBar bar;
	private int nb;
	
	public ThreadProgressBar(JProgressBar bar, int nb){
		this.bar = bar;
		this.nb = nb;
	}
	
	public void run(){
		this.bar.setValue(this.bar.getValue()+1);
		if(nb == 1){
			this.bar.setString(this.bar.getValue()+" Paire(s) crée(s)");
		}
		else if(nb == 0){
			this.bar.setString(this.bar.getValue()+" Match(s) crée(s)");
		}
		this.bar.repaint();
	}

	
	
	

}
