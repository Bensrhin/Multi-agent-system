import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.*;

public class Shelling extends GrilleJeu{
    LinkedList<Point> vacants;
    int seuil;

    public Shelling(int mat[][], int nb_etats, GUISimulator gui, int no_jeu, LinkedList<Point> vacants, int seuil, int N, int M){
          super(mat, nb_etats, gui, no_jeu, N, M);
          this.vacants = vacants; this.seuil = seuil;
      }


    public int[][] update_mat(int[][] mat){
      //Mise à jour de la matrice pour le modèle de Shelling
      //
      //   // l'état correspondant à la valeur 0 est celui d'un logement vacant, les autres
      //   // des logements habités par une certaine couleur
        int resultat[][] = new int[N][M];
        int[] voisins = new int[8];

        LinkedList<Point> history = new LinkedList<Point>();// on y stocke les logements déménagés
        // durant une itération,ceci pour éviter une situation ou deux logements échangent de place par exemple

        for (int i =0; i<N; i++){
          for (int j = 0; j<M; j++){
            resultat[i][j] = mat[i][j];
          }
        }

        for (int i =0; i<N; i++){
          for (int j = 0; j<M; j++){
            Point p0 = new Point(i,j);
            if (history.indexOf(p0)==-1 && mat[i][j]!=0){//p0 n'est pas dans history, et n'est pas vacant
              voisins[0] = mat[(i-1+N)%N][(j-1+M)%M]; voisins[1] = mat[(i-1+N)%N][j];
              voisins[2] = mat[(i-1+N)%N][(j+1)%M]; voisins[3] = mat[i][(j-1+M)%M];
              voisins[4] = mat[i][(j+1)%M]; voisins[5] = mat[(i+1)%N][(j-1+M)%M];
              voisins[6] = mat[(i+1)%N][j]; voisins[7] = mat[(i+1)%N][(j+1)%M];
              int c = 0;
              for (int k = 0; k<8; k++){
                if (voisins[k]!=mat[i][j] && voisins[k]!=0){
                  c++;
                }
              }
              if (c>=this.seuil){ //déménagement
                Point nv = this.vacants.getFirst();
                resultat[(int) nv.getX()][(int) nv.getY()] = mat[i][j];
                resultat[i][j] = 0;
                this.vacants.add(new Point(i,j));
                history.add(new Point((int) nv.getX(), (int) nv.getY()));
                this.vacants.removeFirst();
              }
            }
          }
        }
        // System.out.println("Itération accomplie!");
        return(resultat);
    }




}
