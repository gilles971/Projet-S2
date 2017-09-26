package Thread;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import IHM.MainWindows;
import Main.Tour;
import Main.Tournois;
import Main.database;
import Exception.*;
public class ThreadCreerPaire extends Thread {
	
	private ActionEvent e;
	
	
	
	public void run(){
		
		int ntour;
		try {
			ntour = Tour.nbtour()+1;
			database.updatetournois("update tournois t set t.numtour = "+ntour+" where t.nom = '"+Tournois.getNom()+"'");
			Tournois.setNbToursEffectif(ntour);
			Tour.creerEquipe();
			MainWindows.maj(this.e);
		} catch (ExceptionCreerPaire ep){
			throwError.doError(ep);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	

}
