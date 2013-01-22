import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import java.util.Collection;
import java.util.Arrays;

@RunWith(value=Parameterized.class)
public class RazporedTest2 extends TestCase
{ private int stEkip;
  private int krog;
  private String krogStr;

  public RazporedTest2(int n, int k, String s)
  {
    stEkip = n;
    krog = k;
    krogStr = s;
  } 
  
  @Parameters public static Collection<?> data()
  {
    Object[][] data = new Object[][]{
    {8, 1, "1-8,2-7,3-6,4-5"},
    {8, 2, "1-7,8-6,2-5,3-4"},
    {8, 6, "1-3,4-2,5-8,6-7"},
    {14, 9, "1-6,7-5,8-4,9-3,10-2,11-14,12-13"}
    };

    return(Arrays.asList(data));
  }

  @Test public void preveriKrog()
  {
    Razpored raz = new Razpored(stEkip);
    raz.nastaviKrog(krog);
    String s = raz.razpored();
    
    assertTrue(s.equals(krogStr));
  }  


}