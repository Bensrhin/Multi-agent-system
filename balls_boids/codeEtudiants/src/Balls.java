/**
@author Nabil Bensrhier
@version 0.01
*/
import java.awt.Point;
import java.math.*;

public class Balls{
    // déclaration des attributs:
    // sous forme d'un vecteur de points(balls):
    private Pointf[] balls;
    private Pointf[] iniBalls;
    private int nbBalls;
    private Segment[] carre;
    // Constructeur:
    public Balls(int nbBalls, int height, int width){
      this.nbBalls = nbBalls;
      System.out.println(this.nbBalls);
      this.balls = new Pointf[nbBalls];
      this.iniBalls = new Pointf[nbBalls];
      this.carre =  this.segments_carre(width, height);
      for (int i=0; i<nbBalls; i++){
        this.balls[i] = new Pointf(Math.random() * width, Math.random() * height);
        this.iniBalls[i] = new Pointf(this.balls[i].getX(), this.balls[i].getY());

      }
    }
    public Balls(){
      this(5, 500, 500);
    }
    public Balls(int height, int width){
      this((int)(Math.random()*7 + 3), height, width); //
    }
    public Pointf[] getBalls(){
      return this.balls;
    }
    public int getNbBalls(){
      return this.nbBalls;
    }
    // Méthodes:
    public void bonne_position(Pointf prec, Pointf suiv, double dx, double dy){
      Segment segment = new Segment(prec, suiv);
      // System.out.print(segment.points[0].toString());
      // System.out.println(segment.points[1].toString());
      Pointf intersection;
      for (int i=0; i<4; i++){
        if (segment.intersection(this.carre[i]) != null){
          System.out.println(segment.intersection(this.carre[i]).toString());
          Pointf p  = segment.intersection(this.carre[i]);
          suiv.setLocation(p.getX(), p.getY());
          this.sens(i, suiv);
          suiv.translate(dx, dy);
        }

      }
    }
    public void sens(int index, Pointf suiv){
      if (index == 0 | index == 3){
        suiv.sensy *= -1;
      }
      else{
        suiv.sensx *= -1;
      }
    }
    public void translate(int dx, int dy){
      for (Pointf ball : this.balls){
        Pointf prec = new Pointf(ball.getX(), ball.getY());

        ball.translate((double)dx, (double)dy);
        this.bonne_position(prec, ball, dx, dy);

      }
    }
    public void reInit(){
      for (int i=0; i<this.nbBalls; i++){
        this.balls[i].setLocation(this.iniBalls[i].getX(), this.iniBalls[i].getY());
      }
    }

    public Segment[] segments_carre(int width, int height){
      Segment[] carre = new Segment[4];
      int position = 0;
      Pointf[] sommets = new Pointf[4];
      Segment segment;

      for (int i=0; i<4; i++){
        sommets[i] = new Pointf(width * (i%2), height * (i/2));
        // System.out.println(sommets[i].toString());
      }
      for (int i=0; i<3; i++){
        for (int j=i+1; j<4; j++){
          segment = new Segment(sommets[i], sommets[j]);

          if (segment.longueur() == width | segment.longueur() == height){
            // System.out.print(sommets[i].toString());
            // System.out.println(sommets[j].toString());
            carre[position] = segment;
            position += 1;

          }
        }

      }
      return carre;
    }

    @Override
    public String toString(){
      String positions = new String("Les positions des balles sont les suivantes: \n");
      for (Pointf ball : this.balls){
        positions += "(x,y) : " + ball.toString() + "\n";
      }
      return positions;
    }
}
