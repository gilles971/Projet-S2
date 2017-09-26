package IHM;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class PanelImage extends JPanel {
	
    public Image img;
     
    public PanelImage(Image imageIcon){
        this.img = imageIcon;
    }
     
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
	
    public void setImg(Image imageicon){
    	this.img = imageicon;
    }

}
