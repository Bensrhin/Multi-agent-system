/**
@author Nabil Bensrhier
@version 0.01
*/
import java.awt.Point;
import java.math.*;

public class Boids{
    // déclaration des attributs:
    // sous forme d'un vecteur de points(Boids):
    private Boid[] boids;
    public Boid[] boids_t_1;
    private Boid[] iniBoids;
    private int nbBoids;
    private int width;
    private int height;
    // private Segment[] carre;

    // Constructeur:
    public Boids(int nbBoids, int height, int width){
      this.width = width;
      this.height = height;
      this.nbBoids = nbBoids;
      System.out.println(this.nbBoids);
      this.boids = new Boid[nbBoids];
      this.boids_t_1 = new Boid[nbBoids];
      this.iniBoids = new Boid[nbBoids];
      for (int i=0; i<nbBoids; i++){
        Pointf position = new Pointf(Math.random() * width, Math.random() * height);
        this.boids[i] = new Boid(position);
        this.boids_t_1[i] = new Boid(position);
        this.iniBoids[i] = new Boid(position);
      }
    }
    public Boids(){
      this(5, 500, 500);
    }
    public Boids(int height, int width){
      this((int)(Math.random()*7 + 20), height, width); //
    }
    public Boid[] getBoids(){
      return this.boids;
    }
    public int getNbBoids(){
      return this.nbBoids;
    }

    // Méthodes:
    /* rule 1*/
    public Pointf rule1(Boid boid_j, Boid[] target, boolean first)
    {
      Pointf pc = new Pointf(0, 0);
      int nbV = 0;
      for (Boid boid : this.boids_t_1){
        if (boid.estVoisin(boid_j, 100) == 1 && boid.alive == 1)
        {
          nbV += 1;
          pc = pc.Vector_add(pc, boid.position, 1);
        }
      }
      pc = (nbV == 0)? pc :pc.Vector_mult(pc, (double)1/(nbV));
      pc = pc.Vector_add(pc, boid_j.position, -1);
      pc = pc.Vector_mult(pc, 0.01);
      Pointf pc1 = new Pointf(0, 0);
      if (first)
      {

        nbV = 0;
        for (Boid boid : target){
          if (boid.estVoisin(boid_j, 500) == 1 && boid.alive == 1)
          {
            nbV += 1;
            pc1 = pc.Vector_add(pc1, boid.position, 1);
          }
        }
        pc1 = (nbV == 0)? pc1 :pc.Vector_mult(pc1, (double)1/(nbV));
        pc1 = pc.Vector_add(pc1, boid_j.position, -1);
        pc1 = pc.Vector_mult(pc1, 0.008);
      }
      pc = pc.Vector_add(pc1, pc, 1);

      return pc;
    }

    /* rule 2*/
    public Pointf rule2(Boid boid_j, Boid[] source, boolean last)
    {
      Pointf pc = new Pointf(0, 0);
      for (Boid boid : this.boids_t_1){
        if (boid.estVoisin(boid_j, 200) == 1 && boid.alive == 1)
        {
          double distance = boid_j.position.distance(boid.position);
          if (distance < 30){
            Pointf diff = pc.Vector_add(boid.position, boid_j.position, -1);
            pc = pc.Vector_add(pc, diff, -1);
          }
        }
      }
      Pointf pc1 = new Pointf(0, 0);
      if (last)
      {

        for (Boid boid : source){
          if (boid.estVoisin(boid_j, 200) == 1 && boid.alive == 1)
          {
            double distance = boid_j.position.distance(boid.position);
            if (distance < 100){
              Pointf diff = pc1.Vector_add(boid.position, boid_j.position, -1);
              pc1 = pc1.Vector_add(pc, diff, -1);
            }
          }
        }
      }
      pc = pc.Vector_add(pc1, pc, 1);
      return pc;
    }
      /* rule 3*/
      public Pointf rule3(Boid boid_j)
      {
        int nbV = 0;
        Pointf pc = new Pointf(0, 0);
        for (Boid boid : this.boids_t_1){
          if (boid.estVoisin(boid_j, 20) == 1 && boid.alive == 1)
          {
            nbV += 1;
            pc = pc.Vector_add(pc, boid.velocity, 1);
          }
        }
        pc = (nbV == 0)? pc :pc.Vector_mult(pc, (double)1/(nbV));
        pc = pc.Vector_add(pc, boid_j.velocity, -1);
        pc = pc.Vector_mult(pc, (double)1/4);
        return pc;
      }

      public void move_all_boids_to_new_positions(Boid[] source, Boid[] target, boolean first, boolean last)
      {
        Pointf v1, v2, v3;
        for (Boid boid : this.boids){
          if (boid.alive == 1)
          {
            Pointf place = boid.bound_position(0, this.width, 0, this.height);
            // boid.tend_to_palce(place);
            v1 = rule1(boid, target, first);
            v2 = rule2(boid, source, last);
            v3 = rule3(boid);
            v1 = v1.Vector_add(v1, place, 1);
            v1 = v1.Vector_add(v1, v2, 1);
            v1 = v1.Vector_add(v1, v3, 1);
            boid.setVelocity(v1.Vector_add(boid.velocity, v1, 1));
            boid.limit_velocity();
            boid.setPosition(v1.Vector_add(boid.velocity, boid.position, 1));
          }
        }
        for (int i=0; i<nbBoids; i++){
          this.boids_t_1[i].setPosition(this.boids[i].position);
          this.boids_t_1[i].setVelocity(this.boids[i].velocity);
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
    // public void translate(int dx, int dy){
    //   for (Pointf boid : this.boids){
    //     Pointf prec = new Pointf(boid.getX(), boid.getY());
    //
    //     boid.translate((double)dx, (double)dy);
    //     this.bonne_position(prec, boid, dx, dy);
    //
    //   }
    // }
    public void reInit(){
      for (int i=0; i<this.nbBoids; i++){
        this.boids[i].position.setLocation(this.iniBoids[i].position.getX(), this.iniBoids[i].position.getY());
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
      String positions = new String("Les positions des boides sont les suivantes: \n");
      for (Boid boid : this.boids){
        positions += "(x,y) : " + boid.position.toString() + "\n";
      }
      return positions;
    }
}
