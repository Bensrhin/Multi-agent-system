/**
@version finale
*/
import java.awt.Point;
import java.math.*;
import java.lang.Math;

/****** la création d'une nouvelle classe point avec d'autre méthodes *********/
public class Pointf extends Point{
  /* les coordonnées du point */
  private double xd;
  private double yd;
  /* le sens du translation soit dans le sens positif ou négatif */
  public double sensx;
  public double sensy;
  /* le pas selon x et y */
  private double pasX;
  private double pasY;
  //new constructor
  public Pointf(double x, double y){
    this.xd = x;
    this.yd = y;
    /*  initialisation des pas d'une manière probabiliste */
    this.pasX = (int)(Math.random() * 7 + 2);
    this.pasY = (int)(Math.random() * 7 + 2);
    /*  initialisation du sens d'une manière probabiliste */
    double sens[] = {-1., 1., 1, -1, 0.};
    /* le 1 signifie le sens direct -1 sens inverse */
    this.sensx = sens[(int)(5*Math.random())];
    this.sensy = (sensx == 0)?1:sens[(int)(5*Math.random())];
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
  public void translate(){
    this.xd += pasX*this.sensx;
    this.yd += pasY*this.sensy;
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
