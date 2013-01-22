import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import java.util.Collection;
import java.util.Arrays;

@RunWith(value=Parameterized.class)
public class RazporedTest extends TestCase
{ private int stEkip;
  private int krog;
  private int ekipa1;
  private int ekipa2;
  
  public RazporedTest(int n, int k, int e1, int e2)
  {
    stEkip = n;
    krog = k;
    ekipa1 = e1;
    ekipa2 = e2;
  }


  @Parameters public static Collection<?> data()
  {
    Object[][] data = new Object[][]{
    {8, 1, 3, 6},
    {8, 2, 4, 3},
    {8, 3, 7, 5},
    {8, 4, 8, 2},
    {12, 5, 3, 2},
    {2, 1, 1, 2},
    {2, 3, 2, 1},
    {64, 17, 28, 5}
    };

    return(Arrays.asList(data));
  }

  @Test public void preveri()
  {
    Razpored raz = new Razpored(stEkip);
    raz.nastaviKrog(krog);
    int tmp;

    tmp = raz.nasprotnik(ekipa1);

    assertEquals(ekipa2, tmp);

  }

}