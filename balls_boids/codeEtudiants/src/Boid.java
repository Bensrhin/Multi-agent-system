/**
@author Nabil Bensrhier
@version 0.01
*/
import java.awt.Point;
import java.math.*;
import java.lang.Math;

public class Boid{
  public Pointf velocity;
  public Pointf position;
  public int alive;

  //new constructor
  public Boid(Pointf position, Pointf velocity){
    this.position = new Pointf(position.getX(), position.getY());
    this.velocity = new Pointf(velocity.getX(), velocity.getY());
    this.alive = 1;
  }

  public Boid(Pointf position)
  {
    this(position, new Pointf(0, 0));
  }
  public Boid()
  {
    this(new Pointf(0, 0));
  }
  public void setVelocity(Pointf velocity){
    this.velocity.setLocation(velocity.getX(), velocity.getY());
  }
  public void setPosition(Pointf position){
    this.position.setLocation(position.getX(), position.getY());
  }

  public void limit_velocity()
  {
    double vlim = (Math.random() + 5);
    Pointf v = new Pointf(0, 0);
    double distance = this.velocity.distance(new Pointf(0, 0));
    if (distance > vlim)
    {
      this.setVelocity(v.Vector_mult(this.velocity, (double)vlim / distance));
    }
  }
  public int estVoisin(Boid b, double max)
  {
    double distance = this.position.distance(b.position);
    if (distance > 0 && distance < max)
    {
      return 1;
    }
    return 0;
  }
  public Pointf bound_position(int Xmin, int Xmax, int Ymin, int Ymax)
  {

    Pointf v;
    double vx =  0;
    double vy =  0;
    if (this.position.getX() < Xmin)
    {
      vx = +10;
    }
    else if (this.position.getX() > Xmax)
    {
      vx = -10;
    }
    if (this.position.getY() < Ymin)
    {
      vy = +10;
    }
    else if (this.position.getY() > Ymax)
    {
      vy = -10;
    }
    v = new Pointf(vx, vy);
    return v;
  }
  public void tend_to_palce(Pointf place)
  {
    Pointf translate = this.position.Vector_add(place, this.position, -1);
    translate = translate.Vector_mult(translate, 0.01);
    this.position = translate.Vector_add(translate, this.position, 1);
  }
  // @Override
  // public double getX(){
  //   return this.xd;
  // }
  // @Override
  // public double getY(){
  //   return this.yd;
  // }
  // @Override
  // public String toString(){
  //   String positions = new String("[");
  //   positions += "x = " + this.getX() + ", y = " + this.getY() + "]";
  //   return positions;
  // }

}
