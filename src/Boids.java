import java.awt.Point;

class Boids{
  public Point position;
  public Point vitesse;

  public Boids(Point position, Point vitesse){
    this.position = position;
    this.vitesse = vitesse;
  }

  public Boids deepCopy(){//au cas où..
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
