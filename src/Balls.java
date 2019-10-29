import java.awt.Point;

class Balls{
  public int nombre;
  public Point[] balls;

  public Balls(int l, Point[] b){
    this.nombre = l;
    this.balls = b;
  }

  public void translate(int dx, int dy){
    Point[] b = this.balls;
    for (int i = 0; i<this.nombre; i++){
      b[i].translate(dx, dy);
    }
  }

  public void reInit(){
    Point[] b = this.balls;
    for (int i = 0; i<this.nombre; i++){
      Point p0 = new Point(); //constructeur met le point a 0,0 par defaut
      b[i] = p0;
    }
  }

  @Override
  public String toString() {
    Point[] b = this.balls;
    String s = "(";
    for (int i = 0; i<this.nombre; i++){
      s = s + " " + b[i].toString() + "; ";
    }
    return s+")";
  }
}
