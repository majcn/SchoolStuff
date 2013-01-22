import junit.framework.TestCase;

public class DatumTest extends TestCase
{ private Datum d;

  public void setUp()
  {
    d = new Datum();
  }

  public void tearDown()
  {
    d = null;
  }

  public void test31dni()
  { int r;
    d.nastavi(1, 1, 2007);
    r = d.dniVMesecu();

    assertEquals(31, r);
  }

  public void test30dni()
  {
    d.nastavi(5, 4, 2005);
    assertEquals(30, d.dniVMesecu());
  }

  public void test29dni()
  {
    d.nastavi(16, 2, 2008);
    assertEquals(29, d.dniVMesecu());
  }

  public void test28dni()
  {
    d.nastavi(10, 2, 1999);
    assertEquals(28, d.dniVMesecu());
  }

  public void testWrongDate1()
  {
    d.nastavi(1, 0, 2009);
    assertEquals(0, d.dniVMesecu());
  }

  public void testWrongDate2()
  {
    d.nastavi(13, 13, 2009);
    assertEquals(0, d.dniVMesecu());
  }

  public void testDodaj1()
  { int i, j, k;

    d.nastavi(2, 2, 1998);
    d.dodajDneve(17);

    i = d.vrniDan();
    j = d.vrniMesec();
    k = d.vrniLeto();

    assertEquals(19, i);
    assertEquals(2, j);
    assertEquals(1998, k);
    
  }

  public void testDodaj2()
  { int i, j, k;

    d.nastavi(2, 2, 1998);
    d.dodajDneve(36);

    i = d.vrniDan();
    j = d.vrniMesec();
    k = d.vrniLeto();

    assertEquals(10, i);
    assertEquals(3, j);
    assertEquals(1998, k);
    
  }

  public void testDodaj31()
  { int i, j, k;

    d.nastavi(2, 2, 1998);
    d.dodajDneve(365);

    i = d.vrniDan();
    j = d.vrniMesec();
    k = d.vrniLeto();

    assertEquals(2, i);
    
  }

  public void testDodaj32()
  { int i, j, k;

    d.nastavi(2, 2, 1998);
    d.dodajDneve(365);

    i = d.vrniDan();
    j = d.vrniMesec();
    k = d.vrniLeto();

    assertEquals(2, j);
    
  }

  public void testDodaj33()
  { int i, j, k;

    d.nastavi(2, 2, 1998);
    d.dodajDneve(365);

    i = d.vrniDan();
    j = d.vrniMesec();
    k = d.vrniLeto();

    assertEquals(1999, k);
    
  }



}