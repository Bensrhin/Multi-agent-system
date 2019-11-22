import java.awt.Point;
import gui.*;
import java.awt.Color;


public class TestConway{
  public static void main(String[] args){
    // int mat[][] = {{0, 0, 0, 0, 0}, // exemple donné dans le poly
    //                 {0, 1, 1, 0, 0},
    //                 {0, 1, 0, 1, 0},
    //                 {0, 0, 0, 0, 0},
    //                 {0, 0, 0, 0, 1} };

    int mat[][] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // glider
                    {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0} };

    // int mat[][] = {{0, 0, 0, 0, 0}, // OScillateur
    //                 {0, 0, 0, 0, 0},
    //                 {0, 1, 1, 1, 0},
    //                 {0, 0, 0, 0, 0},
    //                 {0, 0, 0, 0, 0} };

    GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
    gui.setSimulable(new Conway(mat, gui));
  }
}
