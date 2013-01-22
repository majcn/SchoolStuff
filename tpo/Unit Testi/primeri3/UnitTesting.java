import junit.framework.TestSuite;

public class UnitTesting extends TestSuite
{
  public static TestSuite suite()
  { TestSuite s = new TestSuite("Testiranje razreda Datum");
    
    s.addTestSuite(DatumTest.class);

    return(s);

  }

  public static void main(String[] args)
  {
    junit.swingui.TestRunner.run(UnitTesting.class);
  }
}