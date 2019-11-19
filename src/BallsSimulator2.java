import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.Random;


public class BallsSimulator2 implements Simulable{
  public Oval[] balles;
  public int[] directions_X;
  public int[] directions_Y;
  public int[] pas_X;
  public int[] pas_Y;
  public EventManager eventManager;

  public BallsSimulator2(Oval[] balles){
      this.balles = balles;
      this.directions_X = new int[balles.length];
      this.directions_Y = new int[balles.length];

      Random gen = new Random();
      this.pas_X = new int[balles.length];
      this.pas_Y = new int[balles.length];
      for (int i = 0; i<balles.length; i++){
        this.directions_X[i] = 1; this.directions_Y[i] = 1;
        this.pas_X[i] = gen.nextInt(10)+50;this.pas_Y[i] = gen.nextInt(10)+50;
      }

      eventManager = new EventManager();
      eventManager.addEvent(new EventBalls(1, this));
  }

  @Override
  public void next(){
    for (int i =0; i<balles.length; i++){
      System.out.println(directions_X[i]);
    }
    eventManager.next();
  }

  @Override
  public void restart(){
    for (int i = 0; i<this.balles.length; i++){
      this.balles[i].translate(-this.balles[i].getX() + i*100, -this.balles[i].getY() + i*50);
    }
  }
}
