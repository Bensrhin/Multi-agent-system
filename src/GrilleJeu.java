import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.*;


public abstract class GrilleJeu implements Simulable{
  int N; int M;
  int mat[][];
  int mat_copy[][] ; //pour restart
  int nb_etats;
  GUISimulator gui;
  EventManager eventManager;

  public GrilleJeu(int mat[][], int nb_etats, GUISimulator gui){
          this.mat = mat; this.nb_etats = nb_etats ;this.gui = gui;
          this.N = mat.length; this.M = mat[0].length;
          // eventManager = new EventManager();
          // eventManager.addEvent(new EventJeu(0, this));

          mat_copy =  new int[N][M];//copie matrice
          for (int i = 0; i<N; i++){
            for (int j = 0; j<M; j++){
              mat_copy[i][j] = mat[i][j];
            }
          }
      }

  public void update_GUI(int[][] mat, int nb_etats, GUISimulator gui){
    // Affichage de la matrice à nb_etats états, couleurs graduées du blanc au noir
    // 2 états pour Conway, K pour le jeu d'immigration et 3 pour Shelling
    for (int i = 0; i<N; i++){
      for (int j = 0; j<M; j++){
        int k = mat[i][j]; //état de la cellule
        int color = (int) 255- (k*255/(nb_etats-1)); //couleur (entre blanc0x000000 et noir 0xffffff)
        Rectangle rec = new Rectangle (i*500/mat.length+50, j*500/mat.length+50,
                  Color.decode ("#ff0000"), Color.decode ("#"+Integer.toHexString(color)
                  +Integer.toHexString(color)+Integer.toHexString(color)) , 500/mat.length);
        gui.addGraphicalElement(rec); //je n'ai pas trouvé comment changer la couleur..
      }
    }
  }

  public abstract int[][] update_mat(int[][] mat);

  @Override
  public void next(){
    // eventManager.next();
    this.update_GUI(this.mat, this.nb_etats, this.gui);
    this.mat = update_mat(this.mat);
  }

  public void restart(){
    this.update_GUI(this.mat_copy, this.nb_etats, this.gui);
    this.mat = this.update_mat(this.mat_copy);
  }

}
