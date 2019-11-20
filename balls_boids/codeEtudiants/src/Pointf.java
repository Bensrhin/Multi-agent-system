/**
@author Nabil Bensrhier
@version 0.01
*/
import java.awt.Point;
import java.math.*;
import java.lang.Math;

public class Pointf extends Point{
  private double xd;
  private double yd;
  private double sens[] = {-1., 1., 1., -1, 0.};
  public double sensx;
  public double sensy;

  //new constructor
  public Pointf(double x, double y){
    this.xd = x;
    this.yd = y;
    this.sensx = sens[(int)(5*Math.random())];
    this.sensy = sens[(int)(5*Math.random())];
  }

  /* addition of two Pointfs */
  public Pointf Vector_add(Pointf v1, Pointf v2, int add)
  {
    double vx = v1.getX() + add * v2.getX();
    double vy = v1.getY() + add * v2.getY();
    Pointf v = new Pointf(vx, vy);
    return v;
  }
  /* multiplication by a scalar */
  public Pointf Vector_mult(Pointf v1, double A)
  {
    double vx = v1.getX() * A;
    double vy = v1.getY() * A;
    Pointf v = new Pointf(vx, vy);
    return v;
  }


  @Override
  public void setLocation(double x, double y){
    this.xd = x;
    this.yd = y;
  }
  public double distance(Pointf p){
    double diffx = this.getX() - p.getX();
    double diffy = this.getY() - p.getY();
    return Math.sqrt(diffx*diffx + diffy*diffy);
  }
  public void translate(double dx, double dy){
    this.xd += dx*this.sensx;
    this.yd += dy*this.sensy;
  }
  @Override
  public double getX(){
    return this.xd;
  }
  @Override
  public double getY(){
    return this.yd;
  }
  @Override
  public String toString(){
    String positions = new String("[");
    positions += "x = " + this.getX() + ", y = " + this.getY() + "]";
    return positions;
  }

}
