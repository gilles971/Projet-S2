package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/** 
 *  
 * @classe Chronometre 
 *  
 * @extends JPanel 
 *  
 * @description Classe qui définit un chronometre
 * 
 */
public class Chrono extends JPanel {

    /** serialVersionUID : numero de serie */
    private static final long serialVersionUID = 1L;
    
    /** f : Font appliqué au texte */
    private static Font f = new Font("Book Antiqua", Font.BOLD, 25);
    
    /** timer : timer servant à décrémenter le chronometre */
    private Timer timer;
    
    /** couleur : couleur de fond du chronometre */
    private Color couleur=Color.orange;
    
    /** tempsRestant : temps restant */
    private int tempsRestant;  
    
    /** temps : temps initial */
    private int temps;
    
    /** 
     * Construction du chronometre 
     * @param N : le nombre de secondes initial 
     */
    public Chrono(int N){
        timer = createTimer ();
        timer.start();
        setOpaque(false);
        this.setTempsRestant(N);
        this.setTemps(N);
    }
    
    
    /** 
     * Permet de démarrer le chronometre 
     */
    public void start(){
        timer.start ();
    }
    
    /** 
     * Permet d'arreter le chronometre 
     */
    public void stop(){
        timer.stop ();
    }
    
    /** 
     * Methode qui crée un Timer 
     * @return le timer 
     */
    private Timer createTimer (){
        ActionListener action = new ActionListener (){
            public void actionPerformed (ActionEvent event){
                if(tempsRestant>0){
                    tempsRestant--;
                    repaint();
                }
                else{
                    timer.stop();
                    // ------------------- ici foutre une sorte de trigger pour le son ------------------
                }
            }
        };
        return new Timer (1000, action);
    }
    
    
    public Timer getTimer() {
        return timer;
    }
    
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    public int getTempsRestant() {
        return tempsRestant;
    }
    
    public void setTempsRestant(int tempsRestant) {
        this.tempsRestant = tempsRestant; 
    }
    
    public int getTemps() {
        return temps;
    }
    
    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    public void paintComponent(Graphics g) {
        this.drawString(g, 0, 0, 0);
    }
    
    public void drawString(Graphics cg, int xCenter, int yCenter, int r) {
        cg.setFont(f);
        if(tempsRestant>9){
            cg.drawString(""+this.timeToHMS(this.getTempsRestant()), 0, 20);
        }
        else{
            cg.drawString("0"+this.timeToHMS(this.getTempsRestant()), 0, 20);
        }
    }
    
    public static String timeToHMS(long tempsS) {

        // IN : (long) temps en secondes
        // OUT : (String) temps au format texte : "1 h 26 min 3 s"

        int h = (int) (tempsS / 3600);
        int m = (int) ((tempsS % 3600) / 60);
        int s = (int) (tempsS % 60);

        String r="";

        if(h>0) {r+=h+" h ";}
        if(m>0) {r+=m+" min ";}
        if(s>0) {r+=s+" s";}
        if(h<=0 && m<=0 && s<=0) {r="0 s";}

        return r;
        }
    
}