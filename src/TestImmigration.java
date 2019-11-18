import java.awt.Point;
import gui.*;
import java.awt.Color;
import java.util.Random;


public class TestImmigration{
  public static void main(String[] args){
    int N = 30; int M = 30;
    int NB_ETATS = 4;

    int mat0[][] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // exemple du poly
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 3, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 3, 1, 1, 1, 2, 0, 0, 0},
                    {0, 0, 1, 1, 3, 2, 2, 0, 0, 0},
                    {0, 0, 0, 1, 2, 2, 2, 0, 0, 0},
                    {0, 0, 0, 3, 2, 2, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0} };


    int mat_aleatoire[][] = new int[N][M]; // matrice aleatoire
    Random gen = new Random();
    for(int i=0;i<N;i++){
      for(int j=0;j<M;j++){
        mat_aleatoire[i][j] = gen.nextInt(NB_ETATS);
      }
    }

    GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
    gui.setSimulable(new Immigration(mat_aleatoire, NB_ETATS, gui, 1, N, M));
  }
}
