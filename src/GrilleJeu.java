import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.*;


public class GrilleJeu implements Simulable{
  int N = 10; int M = 10;
  int mat[][];
  int nb_etats;
  int no_jeu; //0:Conway; 1:Immigration; 2:Shelling
  GUISimulator gui;
  public GrilleJeu(int mat[][], int nb_etats, GUISimulator gui, int no_jeu){
          this.mat = mat; this.nb_etats = nb_etats ;this.gui = gui; this.no_jeu = no_jeu;
      }

  public void update_GUI(int[][] mat, int nb_etats, GUISimulator gui){
    // Affichage de la matrice à nb_etats états, couleurs graduées du blanc au noir
    // 2 états pour Conway, K pour le jeu d'immigration et 3 pour Shelling
    for (int i = 0; i<N; i++){
      for (int j = 0; j<M; j++){
        int k = mat[i][j]; //état de la cellule
        int color = (int) 255- (k*255/(nb_etats-1)); //couleur (entre blanc0x000000 et noir 0xffffff)
        Rectangle rec = new Rectangle ((i+1)*20, (j+1)*20,
                  Color.decode ("#ff0000"), Color.decode ("#"+Integer.toHexString(color)
                  +Integer.toHexString(color)+Integer.toHexString(color)) , 20);
        gui.addGraphicalElement(rec); //je n'ai pas trouvé comment changer la couleur..
      }
    }
  }

  public int[][] update_mat_conway(int[][] mat){
    // Mise à jour de la matrice pour le jeu de Conway
    int resultat[][] = new int[N][M];
    for (int i =0; i<N; i++){
      for (int j = 0; j<M; j++){
        resultat[i][j] = mat[i][j];
        int sum = (mat[(i-1+N)%N][(j-1+M)%M] + mat[(i-1+N)%N][j] + mat[(i-1+N)%N][(j+1)%M]) +
        (mat[i][(j-1+M)%M] + mat[i][(j+1)%M]) +
        (mat[(i+1)%N][(j-1+M)%M] + mat[(i+1)%N][j] + mat[(i+1)%N][(j+1)%M]);
        if (mat[i][j]==0 && sum==3){
          resultat[i][j] = 1;
        }
        else if (mat[i][j] == 1 && (sum<=1 || sum>=4)){
          resultat[i][j] = 0;
        }
      }
    }
    return(resultat);
  }

  public int[][] update_mat_immigration(int[][] mat){
    // Mise à jour de la matrice pour le jeu d'immigration
    // Attention : Ce programme envoie automatiquement les résulats au
    // secrétariat de Mme Le Pen, pourvu qu'ils soient informatifs.
    int resultat[][] = new int[N][M];
    int[] voisins = new int[8];
    for (int i =0; i<N; i++){
      for (int j = 0; j<M; j++){
        resultat[i][j] = mat[i][j];
        voisins[0] = mat[(i-1+N)%N][(j-1+M)%M]; voisins[1] = mat[(i-1+N)%N][j];
        voisins[2] = mat[(i-1+N)%N][(j+1)%M]; voisins[3] = mat[i][(j-1+M)%M];
        voisins[4] = mat[i][(j+1)%M]; voisins[5] = mat[(i+1)%N][(j-1+M)%M];
        voisins[6] = mat[(i+1)%N][j]; voisins[7] = mat[(i+1)%N][(j+1)%M];
        int c = 0;
        for (int k =0; k<8; k++){
          if (voisins[k]==mat[i][j]+1){
            c++;
          }
        }
        if (c>=3){
          resultat[i][j] = (resultat[i][j]+1)%this.nb_etats;
        }
      }
    }
    return(resultat);
  }

  @Override
  public void next(){
    this.update_GUI(this.mat, this.nb_etats, this.gui);
    if (this.no_jeu == 0){
      this.mat = update_mat_conway(this.mat);
    }
    else if (this.no_jeu == 1){
      this.mat = update_mat_immigration(this.mat);
    }
  }
  public void restart(){ // ne fait rien, à l'instant
    this.update_GUI(this.mat, this.nb_etats, this.gui);
  }

}
