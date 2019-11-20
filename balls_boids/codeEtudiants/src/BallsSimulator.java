import java.math.*;
import gui.*;
import java.awt.Point;
import java.awt.Color;


public class BallsSimulator implements Simulable{
  // attriblut de type Balls
  private Balls points;
  private Oval[] ovals;
  private int dx;
  private int dy;
  private GUISimulator window;
  private int height;
  private int width;
  private Rectangle bg;


  // constructeur:
  public BallsSimulator(GUISimulator window){
    this.height = window.getPanelHeight();
    this.width = window.getPanelWidth();
    this.dx = (int)(Math.random() * 5 + 5);
    this.dy = (int)(Math.random() * 5 + 5);
    this.points = new Balls(this.height, this.width);
    this.ovals = new Oval[this.points.getNbBalls()];
    this.window = window;
    this.bg = new Rectangle(this.width/2, this.height/2,
    Color.blue, Color.lightGray, this.width, this.height);
    System.out.println(this.points.toString());
    this.update_ovals(this.points);

  }


  public void update_ovals(Balls points){
    for (int i=0; i<this.points.getNbBalls(); i++){
      Point p = this.points.getBalls()[i];
      this.ovals[i] = new Oval((int)(p.getX()), (int)(p.getY()),
      Color.black, Color.red, 20);
    }
    this.affichage(this.window);
  }
  public void affichage(GUISimulator window){
    window.reset();
    window.addGraphicalElement(this.bg);
    for (Oval p : this.ovals){
      window.addGraphicalElement(p);
    }
  }

  //
  //
  //
  // public double equation(Point p1, Point p2, double valeur, boolean abscisse){
  //   // cette equation return soit la valeur de x soit de y
  //   // selon le booleen
  //   // y = a x + b
  //   double a = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
  //   double b = p2.getY() - a * p2.getX();
  //   if (abscisse){
  //     // le cas d'abcisse on renvoie x
  //     return (valeur - b) / a;
  //   }
  //   else
  //   // sinon on renvoie la valeur de y
  //   return a * valeur + b;
  // }
  //
  //
  //

  @Override
  public void next(){
    // le pas doit être choisi d'une manière aléatoire:

    this.points.translate(dx, dy);
    System.out.println(this.points.toString());
    this.update_ovals(this.points);
  }

  @Override
  public void restart(){
    // lecture arrêté et le simulateur revient à l'état initiale:
    this.points.reInit();
    System.out.println(this.points.toString());
    this.update_ovals(this.points);
  }
}
