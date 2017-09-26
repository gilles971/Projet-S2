package Thread;

import java.awt.event.ActionEvent;

import IHM.MainWindows;
import Main.Tour;

public class ThreadMatch extends Thread {
	
	private ActionEvent e;
	
	public ThreadMatch(ActionEvent e){
		this.e = e;
	}
	
	public void run(){
		try {
			Tour.creer_match();
			MainWindows.maj(e);
			new ThreadSelectionTournois(e).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
