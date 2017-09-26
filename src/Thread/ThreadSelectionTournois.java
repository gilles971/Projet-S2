package Thread;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import IHM.IHMTerrain;
import IHM.MainWindows;
import Main.Tournois;

public class ThreadSelectionTournois extends Thread {
	
	private ActionEvent e;
	
	public ThreadSelectionTournois(ActionEvent e){
		this.e = e;
	}
	
	
	public void run(){
		
		
		MainWindows.getMatch().removeAll();
		try {
			int nbterrain = Tournois.getNbTerrain();
			for(int i =1;i<=nbterrain; i++){
				JPanel p = new JPanel();
				p.setLayout(new FlowLayout(FlowLayout.CENTER));
				p.add(new IHMTerrain(i).getTerrain());
				MainWindows.getMatch().add(p);
			}
			MainWindows.maj(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
