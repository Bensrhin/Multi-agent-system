import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.Random;


public class BallsSimulator2 implements Simulable{
  public int nombre;
  public Oval[] balles;
  public int[] directions_X;
  public int[] directions_Y;
  public int[] pas_X;
  public int[] pas_Y;
  public EventManager eventManager;

  public BallsSimulator2(Oval[] balles, int nombre){
      this.nombre = nombre;
      this.balles = balles;
      this.directions_X = new int[nombre];
      this.directions_Y = new int[nombre];

      Random gen = new Random();
      this.pas_X = new int[nombre];
      this.pas_Y = new int[nombre];
      for (int i = 0; i<nombre; i++){
        this.directions_X[i] = 1; this.directions_Y[i] = 1;
        this.pas_X[i] = gen.nextInt(50);this.pas_Y[i] = gen.nextInt(50);
      }

      eventManager = new EventManager();
      eventManager.addEvent(new EventTranslate(1, this));
  }

  @Override
  public void next(){
    eventManager.next();
  }

  @Override
  public void restart(){
    for (int i = 1; i<this.nombre+1; i++){
      this.balles[i].translate(-this.balles[i].getX() + i*100, -this.balles[i].getY() + i*50);
    }
  }
}
