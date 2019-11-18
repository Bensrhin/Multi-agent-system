import java.awt.Point;
import gui.*;
import java.awt.Color;
import java.util.*;


public class TestShelling{
  public static void main(String[] args){
    int N = 50; int M = 50;
    int NB_ETATS = 3;
    int SEUIL = 2;
    int NB_BLANCS = N*M/10;


    int mat_aleatoire[][] = new int[N][M]; // on génère une matrice aleatoire, pas très efficacement apparemment
    Random gen = new Random();
    for(int i=0;i<N;i++){
      for(int j=0;j<M;j++){
        int x;
        do{
          x = gen.nextInt(NB_ETATS);
        }while(x==0);
        mat_aleatoire[i][j] = x;
      }
    }
    for (int i = 0; i<NB_BLANCS; i++){
      int i0; int j0;
      do{
        i0 = gen.nextInt(N);
        j0 = gen.nextInt(M);
      }while(mat_aleatoire[i0][j0]==0);
      mat_aleatoire[i0][j0] = 0;
    }


    LinkedList<Point> vacants = new LinkedList<Point>();
    for (int i=0; i<N; i++){
      for (int j=0; j<M; j++){
        if (mat_aleatoire[i][j]==0){
            vacants.add(new Point(i, j));
        }
      }
    }


    GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
    gui.setSimulable(new Shelling(mat_aleatoire, NB_ETATS, gui, 3, vacants, SEUIL, N, M));

  }
}
