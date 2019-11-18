import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.*;



public class BoidsSimulator implements Simulable{
    Boids[] boids;
    Boids[] boids_copy;
    int NB_BOIDS = 100;
    int DIMENSION = 500;

  public BoidsSimulator(Boids[] boids){
    this.boids = boids;

    boids_copy = new Boids[NB_BOIDS];
    for (int i = 0; i<NB_BOIDS; i++){//deepcopy boids->boids_copy
      Boids b = boids[i];
      boids_copy[i] = new Boids(new Point((int)b.position.getX(), (int)b.position.getY()),
      new Point((int)b.vitesse.getX(), (int)b.vitesse.getY()), b.figure);
    }
  }


  public void update_GUI(){
      for (int i =0; i<NB_BOIDS; i++){
          boids[i].figure.translate((int)boids[i].vitesse.getX(),(int)boids[i].vitesse.getY());
      }
      // apparition d'un point qui sort de l'ecran de l'autre cote, utile si on utilise pas la regle du cadrage
      for (int i =0 ; i<NB_BOIDS; i++){
        if (boids[i].figure.getX()>=DIMENSION){
          int d = boids[i].figure.getX()/DIMENSION;
          boids[i].figure.translate(-d*DIMENSION, 0);
        }
        if (boids[i].figure.getY()>=DIMENSION){
          int d = boids[i].figure.getY()/DIMENSION;
          boids[i].figure.translate(0, -d*DIMENSION);
        }
        if (boids[i].figure.getX()<0){
          int d = boids[i].figure.getX()/DIMENSION;
          boids[i].figure.translate(d*DIMENSION, 0);
        }
        if (boids[i].figure.getY()<0){
          int d = boids[i].figure.getY()/DIMENSION;
          boids[i].figure.translate(0, d*DIMENSION);
        }
      }
  }

  public Point rule1(Boids b){//Cohésion
    Point r = new Point(0, 0);
    int PAR = 100;
    for (int i=0; i<NB_BOIDS; i++){
      if (boids[i].position!=b.position){
        r.x += boids[i].position.getX(); r.y += boids[i].position.getY();
      }
    }
    r.x /= (NB_BOIDS-1); r.y /= (NB_BOIDS-1);
    r.x -= b.position.x; r.y -= b.position.y;
    r.x /= PAR; r.y /= PAR;
    return r;
  }


  public Point rule2(Boids b){//Séparation
    Point c = new Point(0, 0);
    int SEUIL = 10;
    for (int i=0; i<NB_BOIDS; i++){
      if (boids[i].position!=b.position){
        if (boids[i].position.distance(b.position)<SEUIL){
          c.translate(- (int)(boids[i].position.getX()-b.position.getX()),
           -(int)(boids[i].position.getY()-b.position.getY()));
        }
      }
    }
    return c;
  }

  public Point rule3(Boids b){//Alignement
    int PAR = 4;
    Point r = new Point();
    for (int i=0; i<NB_BOIDS; i++){
      if (boids[i].position!=b.position){
        r.translate((int)boids[i].vitesse.getX(), (int)boids[i].vitesse.getY());
      }
    }
    r.x /= (NB_BOIDS-1); r.y /= (NB_BOIDS-1);
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
    int xmin = 50; int ymin = 50; int xmax = 450; int ymax = 450;
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

  public Point strong_wind(int wx, int wy){//bah un peu de vent, c'est normal non?
    return new Point(wx, wy);
  }

  @Override
  public void next(){
      update_GUI();
      for (int i = 0; i<NB_BOIDS; i++){
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


  public void restart(){ // ne fait rien, à l'instant

      for (int i = 0; i<NB_BOIDS; i++){// deepcopy boids_copy->boids
        Boids b = boids_copy[i];
        boids[i] = new Boids(new Point((int)b.position.getX(), (int)b.position.getY()),
        new Point((int)b.vitesse.getX(), (int)b.vitesse.getY()), b.figure);
      }

      for (int i = 0; i<NB_BOIDS; i++){//correction de la position de figure
        Oval f = boids[i].figure;
        f.translate(-(int)(f.getX()-boids[i].position.getX()),-(int)(f.getY()-boids[i].position.getY()));
      }
      update_GUI();
  }

}
