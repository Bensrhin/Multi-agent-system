import java.awt.Point;
import gui.*;
import java.awt.Color;
import java.util.*;


public class TestShelling{
  public static void main(String[] args){
    int N = 50; int M = 50;
    int NB_ETATS = 5;
    int SEUIL = 5;
    int NB_BLANCS = N*M/4;


    int matAleatoire[][] = new int[N][M]; // on génère une matrice aleatoire, pas très efficacement je reconnais
    Random gen = new Random();
    for(int i=0;i<N;i++){
      for(int j=0;j<M;j++){
        int x;
        do{
          x = gen.nextInt(NB_ETATS);
        }while(x==0);
        matAleatoire[i][j] = x;
      }
    }
    for (int i = 0; i<NB_BLANCS; i++){
      int i0; int j0;
      do{
        i0 = gen.nextInt(N);
        j0 = gen.nextInt(M);
      }while(matAleatoire[i0][j0]==0);
      matAleatoire[i0][j0] = 0;
    }

    GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
    gui.setSimulable(new Shelling(matAleatoire, NB_ETATS, gui, SEUIL));

  }
}
