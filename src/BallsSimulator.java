import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;


public class BallsSimulator implements Simulable{
  private Balls balles;
  public BallsSimulator(Balls balles){
    this.balles = balles;
  }

  @Override
  public void next(){
    this.balles.translate(10, 10);
    System.out.println(this.balles);
  }

  @Override
  public void restart(){
    this.balles.reInit();
  }
}
