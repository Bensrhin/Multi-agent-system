import java.awt.Point;
import gui.*;
import java.awt.Color;
import java.util.*;
import java.util.Random;


public class TestBoidsSimulator{
  public static void main(String[] args){
    int DIMENSION = 700;
    GUISimulator gui = new GUISimulator(DIMENSION, DIMENSION, Color.WHITE);

    int NB_BOIDS = 100;
    Boids[] my_boids = new Boids[NB_BOIDS];

    Random gen = new Random();
    for (int i=0; i<2*NB_BOIDS/3; i++){
        Boids b = new Boids(new Point(gen.nextInt(2*DIMENSION/5)+DIMENSION/5, gen.nextInt(2*DIMENSION/5)+DIMENSION/5),
        new Point(0,0), "#0000ff", 5);//les bleus : proies
        my_boids[i] = b;
        gui.addGraphicalElement(b.figure);
    }
    for (int i=2*NB_BOIDS/3; i<NB_BOIDS; i++){
        // boids de  position et vitesse aleatoires
        Boids b = new Boids(new Point(gen.nextInt(2*DIMENSION/5)+DIMENSION/5, gen.nextInt(2*DIMENSION/5)+DIMENSION/5),
        new Point(0,0), "#00ff00", 10);//verts prédateurs
        my_boids[i] = b;
        gui.addGraphicalElement(b.figure);
    }

    gui.setSimulable(new BoidsSimulator(my_boids));

  }
}
