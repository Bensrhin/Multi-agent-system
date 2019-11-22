import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.*;



public class BoidsSimulator implements Simulable{
    Boids[] boids;
    Boids[] proies;
    Boids[] predateurs;
    EventManager eventManager;
    Point[] positionsInit;//pour restart
    GUISimulator gui;

  public BoidsSimulator(Boids[] boids, GUISimulator gui){
    this.boids = boids;this.gui = gui;

    eventManager = new EventManager();
    proies =  Arrays.copyOfRange(boids, 0, 2*boids.length/3);
    predateurs = Arrays.copyOfRange(boids, 2*boids.length/3, boids.length);
    eventManager.addEvent(new EventBoids(0, this, proies, predateurs, 1));//fast-blue-proies
    eventManager.addEvent(new EventBoids(0, this, predateurs, proies, 2));//slow-green-predateurs

    positionsInit = new Point[boids.length];
    for (int i=0; i<boids.length; i++){
      Boids b = boids[i];
      positionsInit[i] = new Point((int)b.position.getX(), (int)b.position.getY());
    }
  }

  @Override
  public void next(){
      eventManager.next();
  }

  public void restart(){
    eventManager.restart();
    eventManager.addEvent(new EventBoids((int)eventManager.currentDate, this, proies, predateurs, 1));
    eventManager.addEvent(new EventBoids((int)eventManager.currentDate, this, predateurs, proies, 2));

    for (int i=0; i<boids.length; i++){
      Boids b = boids[i];
      b.vitesse = new Point(0,0);
      b.figure.translate((int)(positionsInit[i].getX()-b.position.getX()),
      (int)(positionsInit[i].getY()-b.position.getY()));
      b.position = new Point((int)positionsInit[i].getX(),(int)positionsInit[i].getY()) ;
    }
    for (int i =0; i<boids.length; i++){
        boids[i].figure.translate((int)boids[i].vitesse.getX(),(int)boids[i].vitesse.getY());
    }
  }

}
