import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.*;

public class Immigration extends GrilleJeu{

    public Immigration(int mat[][], int nbEtats, GUISimulator gui){
      super(mat, nbEtats, gui);
    }

    public int[][] updateMat(int[][] mat){
        // Mise à jour de la matrice pour le jeu d'immigration
        // Attention : Ce programme envoie automatiquement les résulats au
        // secrétariat de Mme Le Pen, pourvu qu'ils soient informatifs..
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
              resultat[i][j] = (resultat[i][j]+1)%this.nbEtats;
            }
          }
        }
        return(resultat);
    }




}
