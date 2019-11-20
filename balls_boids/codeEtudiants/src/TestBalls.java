public class TestBalls {
	public static void main(String[] args) {
    Balls nikes = new Balls();
    System.out.println(nikes.toString());
    nikes.translate(10, 10);
    System.out.println(nikes.toString());
    nikes.reInit();
    System.out.println(nikes.toString());
  }
}
