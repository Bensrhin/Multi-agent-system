import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.Random;

public class EventBalls extends Event {
  public BallsSimulator2 bs;

  public EventBalls(int date, BallsSimulator2 bs){
    super(date);
    this.bs = bs;
  }

  public void execute(){
    for (int i = 0; i<bs.balles.length; i++){
      if (bs.balles[i].getX()+50>=500 || bs.balles[i].getX()-50<0){
        bs.directions_X[i] = -bs.directions_X[i];
      }
      if (bs.balles[i].getY()+50>=500 || bs.balles[i].getY()-50<0){
        bs.directions_Y[i] = -bs.directions_Y[i];
      }
      bs.balles[i].translate(bs.directions_X[i]*bs.pas_X[i], bs.directions_Y[i]*bs.pas_Y[i]);
    }
    bs.eventManager.addEvent(new EventBalls((int)date+1, this.bs));
  }

}
