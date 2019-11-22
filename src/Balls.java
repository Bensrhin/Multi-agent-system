/**
@version finale
*/
import java.awt.Point;
import java.math.*;

public class Balls{
    // déclaration des attributs:
    /*************   Les positions courantes des balles (points) **************/
    private Pointf[] currBalls;
    /*************    Les positions initiales des balles    *******************/
    private Pointf[] iniBalls;
    /************    le nombre de balles choisi  *****************************/
    private int nbBalls;
    /****************** les segments de la fenêtre ************************/
    private Segment[] carre;


    /*************************** Constructeur *************Math.sin(Angle)********************/
    public Balls(int nbBalls, int height, int width){
      this.nbBalls = nbBalls;
      /* ajout des segments de la fenêtre selon
        la longueur et la largeur donnée en paramètre */
      this.carre =  this.segmentsCarre(width, height);
      /*Déclaration des vecteurs de points **/
      this.currBalls = new Pointf[nbBalls];
      this.iniBalls = new Pointf[nbBalls];
      /*Construction aléatoire des balles dans le carré */
      for (int i=0; i<nbBalls; i++){
        currBalls[i] = new Pointf(Math.random() * width, Math.random() * height);
        /*Sans oublier d'enregistrer ces positions initiales */
        iniBalls[i] = new Pointf(currBalls[i].getX(), currBalls[i].getY());
      }
    }
    /*********** Constructeur avec nombre de balles aléatoire *****************/
    public Balls(int height, int width){
      this((int)(Math.random()*12 + 5), height, width); //
    }
    /********************** Constructeur par défauts *************************/
    public Balls(){
      this(500, 500);
    }

    /******************** renvoie le vecteur des balles **********************/
    public Pointf[] getBalls(){
      return this.currBalls;
    }
    /******************** renvoie le nombre des balles **********************/
    public int getNbBalls(){
      return this.nbBalls;
    }
    // Méthodes:
    /***************** On vérifie si la balle reste dans la fenêtre *************/
    public void bonne_position(Pointf prec, Pointf suiv){
      /* construction d'un segment à partir de
        la postion initiale et finale de la balle après le next */
      Segment segment = new Segment(prec, suiv);
      Pointf intersection;
      /* On vérifie si ce segment a des points d'intersection
        avec les segments de la fenêtre */
      for (int i=0; i<4; i++){
        Pointf p  = segment.intersection(this.carre[i]);
        /* si on a une intersection avec la fenêtre*/
        /* et si ce point n'est pas le point du rebond précédent */
        if (p != null){
          double round1 = Math.abs(p.getX() - prec.getX());
          double round2 = Math.abs(p.getY() - prec.getY());
          if ( round1 > 0.001 |  round2 > 0.001){
          /* la balle ne doit pas dépasser la fenêtre:
              Donc il s'agit d'un rebond sur le bord et qui est
              bien sur ce point d'intersection */
          suiv.setLocation(p.getX(), p.getY());
          /*** la balle doit changer le sens selon le bord */
          this.sens(i, suiv);
        }
        }

      }
    }
    /************** la balle change son sens selon la nature du rebond *******/
    public void sens(int index, Pointf suiv){
      /** 0 et 3 représentent les bords horizontales */
      if (index == 0 | index == 3){
        /* donc le sens selon les ordonnées qui va changer*/
        suiv.sensy *= -1;
      }
      /** 1 et 2 représentent les bords verticales */
      else{
        /* donc le sens selon les abscisses qui va changer*/
        suiv.sensx *= -1;
      }
    }
    /************ on translate la balle après le next ************************/
    public void translate(){
      for (Pointf ball : this.currBalls){
        /* on enregistre ce point avant de translater */
        Pointf prec = new Pointf(ball.getX(), ball.getY());
        /* on translate la balle */
        ball.translate();
        /* on enregistre la bonne position pour cette balle */
        this.bonne_position(prec, ball);

      }
    }
    /*********** on fait un reset des balles **********************************/
    public void reInit(){
      for (int i=0; i<this.nbBalls; i++){
        currBalls[i].setLocation(iniBalls[i].getX(), iniBalls[i].getY());
      }
    }
    /********** construction des segments des bords de la fenêtre ************/
    public Segment[] segmentsCarre(int width, int height){
      Segment[] carre = new Segment[4];
      Pointf[] sommets = new Pointf[4];
      Segment segment;
      /* la position des sommets */
      for (int i=0; i<4; i++){
        sommets[i] = new Pointf(width * (i%2), height * (i/2));
      }
      /* construction des segments sans répétition */
      int position = 0;
      for (int i=0; i<3; i++){
        for (int j=i+1; j<4; j++){
          segment = new Segment(sommets[i], sommets[j]);
          double round1 = Math.abs(segment.longueur() - width);
          double round2 = Math.abs(segment.longueur() - height);
          /* vérification de la longueur **/
          if (round1 < 0.01 | round2 < 0.01){
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
      for (Pointf ball : this.currBalls){
        positions += "(x,y) : " + ball.toString() + "\n";
      }
      return positions;
    }
}
