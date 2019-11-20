import java.math.*;
import gui.*;
import java.awt.Point;
import java.awt.Color;


public class BoidsSimulatorGroup extends BoidsSimulator implements Simulable{
  // attriblut de type Boids
  private Boids points;
  private Oval[] ovals;
  private GUISimulator window;
  private int height;
  private int width;
  private Rectangle bg;


  // constructeur:
  public BoidsSimulator(GUISimulator window){
    this.height = window.getPanelHeight();
    this.width = window.getPanelWidth();
    this.points = new Boids(this.height, this.width);
    this.ovals = new Oval[this.points.getNbBoids()];
    this.window = window;
    this.bg = new Rectangle(this.width/2, this.height/2,
    Color.blue, Color.lightGray, this.width, this.height);
    System.out.println(this.points.toString());
    this.update_ovals(this.points);

  }


  public void update_ovals(Boids points){
    for (int i=0; i<this.points.getNbBoids(); i++){
      Point p = this.points.getBoids()[i].position;
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

  @Override
  public void next(){
    this.points.move_all_boids_to_new_positions(this.points.boids_t_1);
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
