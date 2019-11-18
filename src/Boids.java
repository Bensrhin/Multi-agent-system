import java.awt.Point;
import gui.*;
import java.awt.Color;
import java.util.*;
import java.util.Random;



class Boids{
  public Point position;
  public Point vitesse;
  public Oval figure;

  public Boids(Point position, Point vitesse){
    this.position = position;
    this.vitesse = vitesse;
    this.figure = new Oval((int)position.getX(), (int)position.getY(),
     Color.decode ("#1f77b4"), Color.decode ("#1f77b4"), 5);
  }

  public Boids(Point position, Point vitesse, Oval figure){
    this.position = position;
    this.vitesse = vitesse;
    this.figure = figure;
  }

  //
  public Boids deepCopy(){//au cas où.. (A NE PAs UTILISEr, CAR CREER DES NEWS OVALS)
    Boids b2 = new Boids(new Point((int)this.position.getX(), (int)this.position.getY()),
    new Point((int)this.vitesse.getX(), (int)this.vitesse.getY()));
    return b2;
  }

  @Override
  public String toString() {
    return "Boids(" + this.position.toString() + ", " +
            this.vitesse.toString() + ")";
  }
}
