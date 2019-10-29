import java.awt.Point;
import gui.*;
import java.awt.Color;


public class TestConway{
  public static void main(String[] args){
    int N = 5; int M = 5;
    int mat0[][] = {{0, 0, 0, 0, 0}, // etat initial
                    {0, 1, 1, 0, 0},
                    {0, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1} };

    // int mat0[][] = {{0, 0, 0, 0, 0}, // 2eme exemple : oscillateur
    //                 {0, 0, 0, 0, 0},
    //                 {0, 1, 1, 1, 0},
    //                 {0, 0, 0, 0, 0},
    //                 {0, 0, 0, 0, 0} };

    GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
    gui.setSimulable(new Conway(mat0, gui));
  }
}
