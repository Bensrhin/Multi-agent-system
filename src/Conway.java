import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.Random;


public class Conway implements Simulable{
  int mat[][];
  GUISimulator gui;
  public Conway(int mat[][], GUISimulator gui){
          this.mat = mat; this.gui = gui;
      }

  public void update_GUI(int[][] mat, GUISimulator gui){
    // Affichage de la matrice
    int N = 5; int M = 5;
    for (int i = 0; i<N; i++){
      for (int j = 0; j<M; j++){
        if (mat[i][j]==0){
          Rectangle rec = new Rectangle (i*50, j*50,
                    Color.decode ("#808080"), Color.decode ("#ffffff") , 50);
          gui.addGraphicalElement(rec); //je n'ai pas trouvé comment changer la couleur..
        }
        if (mat[i][j]==1) {
          Rectangle rec = new Rectangle (i*50, j*50,
                    Color.decode ("#808080"), Color.decode ("#1f77b4") , 50);
          gui.addGraphicalElement(rec);
        }
      }
    }
  }

  public int[][] update_mat(int[][] mat){
    // Mise à jour de la matrice
    int N = 5; int M = 5;
    int resultat[][] = new int[5][5];
    for (int i =0; i<N; i++){
      for (int j = 0; j<M; j++){
        int sum = (mat[(i-1+N)%N][(j-1+M)%M] + mat[(i-1+N)%N][j] + mat[(i-1+N)%N][(j+1)%M]) +
        (mat[i][(j-1+M)%M] + mat[i][(j+1)%M]) + (mat[(i+1)%N][(j-1+M)%M] + mat[(i+1)%N][j] + mat[(i+1)%N][(j+1)%M]);
        if (mat[i][j]==0 && sum==3){
          resultat[i][j] = 1;
        }
        else if (mat[i][j] == 1 && (sum<=1 || sum>=4)){
          resultat[i][j] = 0;
        }
        else{
          resultat[i][j] = mat[i][j];
        }
      }
    }
    return(resultat);
  }

  @Override
  public void next(){
    this.update_GUI(this.mat, this.gui);
    this.mat = update_mat(this.mat);
  }
  public void restart(){ // ne fait rien
    this.update_GUI(this.mat, this.gui);
  }

}
