import java.awt.Point;
import gui.*;
import java.awt.Color;

import java.util.Random;

public class TestBallsSimulator2{
  public static void main(String[] args){
    int NB =5;
    GUISimulator window = new GUISimulator(500, 500, Color.BLACK);

    Oval[] Balles = new Oval[NB];
    for (int i = 0; i<NB; i++) {
      Random gen = new Random();
      Oval ov = new Oval (gen.nextInt(500), gen.nextInt(500),
                Color.decode ("#1f77b4"), Color.decode ("#1f77b4") , 50);
      window.addGraphicalElement(ov);
      Balles[i] = ov;
    }

    window.setSimulable(new BallsSimulator2(Balles, NB));
  }
}
