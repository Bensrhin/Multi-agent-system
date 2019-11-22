import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.Random;

public class EventBalls extends Event {
  public BallsSimulator bs;

  public EventBalls(int date, BallsSimulator bs){
    super(date);
    this.bs = bs;
  }

  public void execute(){
    bs.points.translate();
    bs.update_ovals(bs.points);
    bs.eventManager.addEvent(new EventBalls((int)this.date+1, this.bs));
  }
}
