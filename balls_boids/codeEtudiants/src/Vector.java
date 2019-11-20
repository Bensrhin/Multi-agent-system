/**
@author Nabil Bensrhier
@version 0.01
*/
import java.awt.Point;
import java.math.*;
import java.lang.Math;

public class Vector{
  private double x;
  private double y;
  // private double sens[] = {-1., 1., 1., -1, 0.};
  // public double sensx;
  // public double sensy;

  //new constructor
  public Vector(double x, double y){
    this.x = x;
    this.y = y;
    // this.sensx = sens[(int)(5*Math.random())];
    // this.sensy = sens[(int)(5*Math.random())];
  }
  public double getX(){
    return this.x;
  }
  public double getY(){
    return this.y;
  }
  /* addition of two vectors */
  public Vector vector_add(Vector v1, Vector v2)
  {
    double vx = v1.getX() + v2.getX();
    double vy = v1.getY() + v2.getY();
    Vector v = new Vector(vx, vy);
    return v;
  }
  /* multiplication by a scalar */
  public Vector vector_mult(Vector v1, int A)
  {
    double vx = v1.getX() * A;
    double vy = v1.getY() * A;
    Vector v = new Vector(vx, vy);
    return v;
  }

  public void setLocation(double x, double y){
    this.x = x;
    this.y = y;
  }
  // public double distance(Pointf p){
  //   double diffx = this.getX() - p.getX();
  //   double diffy = this.getY() - p.getY();
  //   return Math.sqrt(diffx*diffx + diffy*diffy);
  // }
  // public void translate(double dx, double dy){
  //   this.xd += dx*this.sensx;
  //   this.yd += dy*this.sensy;
  // }

  // 
  // @Override
  // public String toString(){
  //   String positions = new String("[");
  //   positions += "x = " + this.getX() + ", y = " + this.getY() + "]";
  //   return positions;
  // }

}
