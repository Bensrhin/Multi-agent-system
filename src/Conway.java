import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.*;

public class Conway extends GrilleJeu{

    public Conway(int mat[][], int nb_etats, GUISimulator gui, int no_jeu, int N, int M){
      super(mat, nb_etats, gui, no_jeu, N, M);
    }

    public int[][] update_mat(int[][] mat){
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




}
