package IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controleur.JoueurControleur;


public class IHMExport extends JFrame{
		
		
		public IHMExport(){
			super("Exporter le tournoi");
			
			try {
				this.setIconImage(ImageIO.read(this.getClass().getResource("UsersAdd32.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
}
}
