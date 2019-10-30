import java.awt.Point;
import gui.*;
import java.awt.Color;
import java.util.Random;


public class TestImmigration{
  public static void main(String[] args){
    int N = 10; int M = 10;

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


    int mat_aleatoire[][] = new int[10][10]; // matrice aleatoire
    Random gen = new Random();
    for(int i=0;i<10;i++){
      for(int j=0;j<10;j++){
        mat_aleatoire[i][j] = gen.nextInt(4);
      }
    }

    GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
    gui.setSimulable(new GrilleJeu(mat_aleatoire, 4, gui, 1));
  }
}
