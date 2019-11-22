import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.Random;

public class EventJeu extends Event {
  GrilleJeu grille;

  public EventJeu(int date, GrilleJeu grille){
    super(date); this.grille = grille;
  }

  public void execute(){
    grille.update_GUI(grille.mat, grille.nbEtats, grille.gui);
    grille.mat = grille.update_mat(grille.mat);
    grille.eventManager.addEvent(new EventJeu((int)this.date+1, grille));
  }

}
