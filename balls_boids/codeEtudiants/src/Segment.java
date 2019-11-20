import java.math.*;


public class Segment{
  // definition d'un segment du plan
  public Pointf[] points = new Pointf[2];
  // constructeur
   public Segment(Pointf p1, Pointf p2){
     this.points[0] = p1;
     this.points[1] = p2;
   }


   // Méthodes
   public double longueur(){
     return this.points[0].distance(this.points[1]);
   }
   public boolean contient(Pointf point){
     double distance = 0.;
     for (Pointf p : this.points){
       distance += p.distance(point);
     }
     double round = Math.abs(this.longueur() - distance);
     return  (0 <= round & round <= 0.01);
   }

   public Pointf intersection(Segment segment){
     // coordonnées du segment 1
     // System.out.print(segment.points[0].toString());
     double x0 = this.points[0].getX();
     double y0 = this.points[0].getY();
     double x1 = this.points[1].getX();
     double y1 = this.points[1].getY();
     // coordonnées du segment 1
     double x2 = segment.points[0].getX();
     double y2 = segment.points[0].getY();
     double x3 = segment.points[1].getX();
     double y3 = segment.points[1].getY();
     // calcule:
     double xv = x1 - x0;
     double yv = y1 - y0;
     double xw = x3 - x2;
     double yw = y3 - y2;
     double denominateur = (xv * yw) - (yv * xw);

     if (denominateur == 0.){

       return null;
     }

     // System.out.println("sommets[i].getX()");
     double alpha = ((y0-y2)*xw - (x0-x2)*yw) / denominateur;
     Pointf intersection = new Pointf(x0 + alpha*xv, y0 + alpha*yv);
     if ( this.contient(intersection) & segment.contient(intersection) ){
       return intersection;
     }
     return null;
   }
}
