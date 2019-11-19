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

  public Boids(Point position, Point vitesse, String color, int size){
    this.position = position;
    this.vitesse = vitesse;
    this.figure = new Oval((int)position.getX(), (int)position.getY(),
     Color.decode (color), Color.decode (color), size);
  }


  @Override
  public String toString() {
    return "Boids(" + this.position.toString() + ", " +
            this.vitesse.toString() + ")";
  }
}
