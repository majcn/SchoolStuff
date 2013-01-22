public class GlavniProgram
{
  public static void main(String[] args)
  {
    Datum d = new Datum();
    int r;

    d.nastavi(1, 2, 2000);

    r = d.dniVMesecu();

    System.out.println(r);
  }
}