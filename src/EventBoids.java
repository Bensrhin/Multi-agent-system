import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.Random;
import java.util.*;

public class EventBoids extends Event {
  BoidsSimulator bs;
  int DIMENSION = 700;
  Boids[] boids;//sur lequels on agit
  Boids[] others;
  int pasTemps;



  public EventBoids(int date, BoidsSimulator bs, Boids[] boids, Boids[] others, int pasTemps){
    super(date); this.bs = bs; this.boids = boids; this.others = others; this.pasTemps = pasTemps;
  }

  public void update_GUI(){
      for (int i =0; i<boids.length; i++){
          boids[i].figure.translate((int)boids[i].vitesse.getX(),(int)boids[i].vitesse.getY());
      }
  }

  public Point rule1(Boids b){//Cohésion
    Point r = new Point(0, 0);
    int PAR = 100;
    for (int i=0; i<boids.length; i++){
      if (boids[i].position!=b.position){
        r.x += boids[i].position.getX(); r.y += boids[i].position.getY();
      }
    }
    r.x /= (boids.length-1); r.y /= (boids.length-1);
    r.x -= b.position.x; r.y -= b.position.y;
    r.x /= PAR; r.y /= PAR;

    if (this.pasTemps==2){//prédateurs se dirigent vers les proies
      Point r1 = new Point(0, 0);
      PAR = 80;
      for (int i=0; i<others.length; i++){
        if (others[i].position!=b.position){
          r1.x += others[i].position.getX(); r1.y += others[i].position.getY();
        }
      }
      r1.x /= (others.length-1); r1.y /= (others.length-1);
      r1.x -= b.position.x; r1.y -= b.position.y;
      r1.x /= PAR; r1.y /= PAR;
      r.x += r1.x; r.y += r1.y;
    }
    return r;
  }


  public Point rule2(Boids b){//Séparation
    Point c = new Point(0, 0);
    int SEUIL = 10;
    for (int i=0; i<boids.length; i++){
      if (boids[i].position!=b.position){
        if (boids[i].position.distance(b.position)<SEUIL){
          c.translate(- (int)(boids[i].position.getX()-b.position.getX()),
           -(int)(boids[i].position.getY()-b.position.getY()));
        }
      }
    }
    if (this.pasTemps==1){ //proies évitent les prédateurs, avec un seuil plus grand biensur
      SEUIL = 30;
      for (int i=0; i<others.length; i++){
        if (others[i].position!=b.position){
          if (others[i].position.distance(b.position)<SEUIL){
            c.translate(- (int)(others[i].position.getX()-b.position.getX()),
             -(int)(others[i].position.getY()-b.position.getY()));
          }
        }
      }
    }
    return c;
  }

  public Point rule3(Boids b){//Alignement
    int PAR = 4;
    Point r = new Point();
    for (int i=0; i<boids.length; i++){
      if (boids[i].position!=b.position){
        r.translate((int)boids[i].vitesse.getX(), (int)boids[i].vitesse.getY());
      }
    }
    r.x /= (boids.length-1); r.y /= (boids.length-1);
    r.x -= b.vitesse.getX(); r.y -= b.vitesse.getY();
    r.x /= PAR; r.y /= PAR;
    return r;
  }

  public void limite_vitesse(Boids b, int vlim){//limite la vitesse des boids
    double abs_vitesse = b.vitesse.distance(new Point(0,0));
    if (abs_vitesse>vlim){
      b.vitesse.setLocation( vlim*b.vitesse.getX()/abs_vitesse , vlim*b.vitesse.getY()/abs_vitesse);
    }
  }

  public Point cadrage(Boids b, boolean on){//cadrer
    int xmin = DIMENSION/10; int ymin = xmin; int xmax = 9*DIMENSION/10; int ymax = xmax;
    Point r = new Point(0,0);
    if (b.position.getX()<xmin){
      r.x = 10;
    }
    else if (b.position.getX()>xmax){
      r.x = -10;
    }
    if (b.position.getY()<ymin){
      r.y = 10;
    }
    else if (b.position.getY()>ymax){
      r.y = -10;
    }
    if (on == false){
      return new Point(0, 0);
    }
    return r;
  }

  public Point strong_wind(int wx, int wy){
    return new Point(wx, wy);
  }


  public void update_boids(){
    for (int i = 0; i<boids.length; i++){
       Point v1 = rule1(boids[i]);
       Point v2 = rule2(boids[i]);
       Point v3 = rule3(boids[i]);
       limite_vitesse(boids[i], 15);
       Point v5 = cadrage(boids[i], true);
       Point v6 = strong_wind(0, 0);
       Point v = new Point((int)(v1.getX()+v2.getX()+v3.getX()+v5.getX()+v6.getX()),
                          (int)(v1.getY()+v2.getY()+v3.getY()+v5.getY()+v6.getY()));
       boids[i].vitesse.translate((int)v.getX(), (int)v.getY());
       boids[i].position.translate((int)boids[i].vitesse.getX(),(int)boids[i].vitesse.getY());
    }
  }

  public void execute(){
    update_GUI();
    update_boids();
    bs.eventManager.addEvent(new EventBoids((int)date+pasTemps, bs, boids, others, pasTemps));
  }

}
