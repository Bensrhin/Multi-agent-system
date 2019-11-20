import java.math.*;
import gui.*;
import java.awt.Point;
import java.awt.Color;


public class BoidsSimulator implements Simulable{
  // attriblut de type Boids
  private Boids[] points = new Boids[2];
  private Oval[][] ovals = new Oval[2][];
  private GUISimulator window;
  private int height;
  private int width;
  private Rectangle bg;


  // constructeur:
  public BoidsSimulator(GUISimulator window){
    this.height = window.getPanelHeight();
    this.width = window.getPanelWidth();

    this.points[0] = new Boids(this.height, this.width);
    this.points[1] = new Boids(this.height, this.width);
    this.ovals[0] = new Oval[this.points[0].getNbBoids()];
    this.ovals[1] = new Oval[this.points[1].getNbBoids()];

    this.window = window;
    this.bg = new Rectangle(this.width/2, this.height/2,
    Color.blue, Color.lightGray, this.width, this.height);
    System.out.println(this.points.toString());
    this.update_ovals(this.points);

  }


  public void update_ovals(Boids[] points){
    for (int i=0; i<this.points[0].getNbBoids(); i++){
      Point p = this.points[0].getBoids()[i].position;
      this.ovals[0][i] = new Oval((int)(p.getX()), (int)(p.getY()),
      Color.black, Color.red, 20);
    }
    for (int i=0; i<this.points[1].getNbBoids(); i++){
      Point p = this.points[1].getBoids()[i].position;
      this.ovals[1][i] = new Oval((int)(p.getX()), (int)(p.getY()),
      Color.black, Color.black, 10);
    }
    this.affichage(this.window);
  }
  public void affichage(GUISimulator window){
    window.reset();
    window.addGraphicalElement(this.bg);
    for (Oval p : this.ovals[0]){
      window.addGraphicalElement(p);
    }
    for (Oval p : this.ovals[1]){
      window.addGraphicalElement(p);
    }
  }

  @Override
  public void next(){
    this.points[0].move_all_boids_to_new_positions(this.points[0].boids_t_1, this.points[1].boids_t_1, true, false);
    this.points[1].move_all_boids_to_new_positions(this.points[0].boids_t_1, this.points[1].boids_t_1, false, true);
    System.out.println(this.points.toString());
    this.update_ovals(this.points);
  }

  @Override
  public void restart(){
    // lecture arrêté et le simulateur revient à l'état initiale:
    this.points[0].reInit();
    this.points[1].reInit();
    System.out.println(this.points.toString());
    this.update_ovals(this.points);
  }
}
