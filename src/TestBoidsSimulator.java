import java.awt.Point;
import gui.*;
import java.awt.Color;
import java.util.*;
import java.util.Random;


public class TestBoidsSimulator{
  public static void main(String[] args){
    GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);

    int NB_BOIDS = 100;
    Boids[] my_boids = new Boids[NB_BOIDS];
    Oval[] my_ovals = new Oval[NB_BOIDS];

    Random gen = new Random();
    for (int i=0; i<NB_BOIDS; i++){
        // boids de  position et vitesse aleatoires
        Boids b = new Boids(new Point(gen.nextInt(200)+100, gen.nextInt(200)+100),
        new Point(0,0));
        my_boids[i] = b;
    }

    for (int i=0; i<NB_BOIDS; i++){
      Boids b = my_boids[i];
      Oval ov = new Oval((int)b.position.getX(), (int)b.position.getY(), Color.decode ("#1f77b4"),
       Color.decode ("#1f77b4"), 5);
      my_ovals[i] = ov;
      gui.addGraphicalElement(ov);
    }

    gui.setSimulable(new BoidsSimulator(my_boids, my_ovals));

  }
}
