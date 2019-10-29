import java.awt.Point;
import gui.Simulable;
import gui.*;
import java.awt.Color;
import java.util.Random;


public class BallsSimulator2 implements Simulable{
  private int nombre;
  private Oval[] balles;
  private int[] directions_X;
  private int[] directions_Y;
  private int[] pas_X;
  private int[] pas_Y;

  public BallsSimulator2(Oval[] balles, int nombre){
      this.nombre = nombre;
      this.balles = balles;
      this.directions_X = new int[nombre];
      this.directions_Y = new int[nombre];

      Random gen = new Random();
      this.pas_X = new int[nombre];
      this.pas_Y = new int[nombre];
      for (int i = 0; i<nombre; i++){
        this.directions_X[i] = 1; this.directions_Y[i] = 1;
        this.pas_X[i] = gen.nextInt(50);this.pas_Y[i] = gen.nextInt(50);
      }
  }

  @Override
  public void next(){
    for (int i = 0; i<this.nombre; i++){
      if (this.balles[i].getX()+50>=500 || this.balles[i].getX()-50<0){
        this.directions_X[i] = -this.directions_X[i];
      }
      if (this.balles[i].getY()+50>=500 || this.balles[i].getY()-50<0){
        this.directions_Y[i] = -this.directions_Y[i];
      }
      this.balles[i].translate(this.directions_X[i]*this.pas_X[i], this.directions_Y[i]*this.pas_Y[i]);
    }
  }

  @Override
  public void restart(){
    for (int i = 1; i<this.nombre+1; i++){
      this.balles[i].translate(-this.balles[i].getX() + i*100, -this.balles[i].getY() + i*50);
    }
  }
}
