import java.lang.Object;
import java.awt.Point;
import gui.GUISimulator;
import java.awt.Color;

public class TestBalls{
  public static void main(String[] args){
    Point b1 = new Point(4, 6);
    Point b2 = new Point(1, 3);
    Point b3 = new Point(0, 0);
    Point[] tab = new Point[3];
    tab[0] = b1; tab[1] = b2; tab[2] = b3;
    Balls set1 = new Balls(3, tab);
    System.out.println(set1);
    set1.translate(55, 64);
    System.out.println(set1);
    set1.reInit();
    System.out.println(set1);
  }
}
