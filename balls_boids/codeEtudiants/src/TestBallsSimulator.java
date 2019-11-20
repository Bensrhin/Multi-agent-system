import gui.GUISimulator;
import java.awt.Color;

public class TestBallsSimulator{
  public static void main(String[] args){
    GUISimulator gui = new GUISimulator (800, 800, Color.BLACK);
    gui.setSimulable(new BallsSimulator(gui));

  }
}
