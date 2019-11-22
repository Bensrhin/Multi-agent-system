import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.*;


public abstract class GrilleJeu implements Simulable{
  int N; int M;
  int mat[][];
  int matCopy[][] ; //pour restart
  int nbEtats;
  GUISimulator gui;
  EventManager eventManager;

  public GrilleJeu(int mat[][], int nbEtats, GUISimulator gui){
          this.mat = mat; this.nbEtats = nbEtats ;this.gui = gui;
          this.N = mat.length; this.M = mat[0].length;
          eventManager = new EventManager();
          eventManager.addEvent(new EventJeu(0, this));

          matCopy =  new int[N][M];//copie matrice
          for (int i = 0; i<N; i++){
            for (int j = 0; j<M; j++){
              matCopy[i][j] = mat[i][j];
            }
          }
      }

  public void updateGUI(int[][] mat, int nbEtats, GUISimulator gui){
    // Affichage de la matrice à nbEtats états, couleurs graduées du blanc au noir
    // 2 états pour Conway, K pour le jeu d'immigration et Shelling
    for (int i = 0; i<N; i++){
      for (int j = 0; j<M; j++){
        int k = mat[i][j];
        int color = (int) 255- (k*255/(nbEtats-1)); //couleur (entre blanc0x000000 et noir 0xffffff)
        Rectangle rec = new Rectangle (i*500/mat.length+50, j*500/mat.length+50,
                  Color.decode ("#ff0000"), Color.decode ("#"+Integer.toHexString(color)
                  +Integer.toHexString(color)+Integer.toHexString(color)) , 500/mat.length);
        gui.addGraphicalElement(rec);
      }
    }
  }

  public abstract int[][] updateMat(int[][] mat);


  @Override
  public void next(){
    gui.reset();
    eventManager.next();
  }

  public void restart(){
    eventManager.restart();
    eventManager.addEvent(new EventJeu((int)eventManager.currentDate, this));

    this.updateGUI(this.matCopy, this.nbEtats, this.gui);
    this.mat = this.updateMat(this.matCopy);
  }

}
