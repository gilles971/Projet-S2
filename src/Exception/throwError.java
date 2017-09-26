package Exception;

import javax.swing.JOptionPane;

public class throwError {

	
public static void doError(ExceptionCreerPaire e){
		JOptionPane.showMessageDialog(null, "Erreur dans la création des paires: \n"+"Plus de combinaison possible." ,"ERREUR", JOptionPane.ERROR_MESSAGE);
	}


}
