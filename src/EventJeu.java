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
    // grille.update_GUI(grille.mat, grille.nb_etats, grille.gui);
    // grille.mat = update_mat(grille.mat);
    // grille.eventManager.add(new EventJeu(this.date+1, this));
  }

}
