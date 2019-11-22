import java.math.*;
import gui.*;
import java.awt.Point;
import java.awt.Color;


public class BallsSimulator implements Simulable{
  // attriblut de type Balls
  public Balls points;
  private Oval[] ovals;
  private GUISimulator window;
  /* largeur et longueur */
  private int height;
  private int width;
  /* background : sous forme rectangle */
  private Rectangle bg;
  private Color ballColor[];

  public EventManager eventManager;

  // constructeur:
  public BallsSimulator(GUISimulator window){
    this.height = window.getPanelHeight();
    this.width = window.getPanelWidth();
    this.points = new Balls(this.height, this.width);
    this.ovals = new Oval[this.points.getNbBalls()];
    this.window = window;
    Color colors[] = {Color.cyan, Color.green, Color.blue, Color.black, Color.pink, Color.darkGray, Color.yellow, Color.red};
    this.ballColor = new Color[this.points.getNbBalls()];
    for (int i = 0; i<this.points.getNbBalls(); i++)
    {
      this.ballColor[i] = colors[(int)(8*Math.random())];
    }
    this.bg = new Rectangle(this.width/2, this.height/2,
    Color.blue, Color.lightGray, this.width, this.height);
    this.update_ovals(this.points);

    eventManager = new EventManager();
    eventManager.addEvent(new EventBalls(0, this));
  }


  public void update_ovals(Balls points){
    for (int i=0; i<this.points.getNbBalls(); i++){
      Point p = this.points.getBalls()[i];
      this.ovals[i] = new Oval((int)(p.getX()), (int)(p.getY()),
      Color.black, ballColor[i], 20);
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
    eventManager.next();
  }

  @Override
  public void restart(){
    // lecture arrêté et le simulateur revient à l'état initiale:
    this.points.reInit();
    this.update_ovals(this.points);
    eventManager.restart();
    eventManager.addEvent(new EventBalls((int)eventManager.currentDate, this));
  }
}
