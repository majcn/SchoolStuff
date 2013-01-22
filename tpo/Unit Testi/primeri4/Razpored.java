public class Razpored
{
  private int stEkip;
  private int krog;  
  private int[] ekipa;

  public Razpored(int n)
  {
    stEkip = n;
    ekipa = new int[n + 1];
  }

  private void init()
  {
    for (int i = 1; i <= stEkip; i++)
      ekipa[i] = i;
  }

  private void premakni()
  {
    int tmp, i;

    tmp = ekipa[stEkip];
    for (i = stEkip; i >= 3; i--)
      ekipa[i] = ekipa[i - 1];

    ekipa[2] = tmp;
  }

  public void nastaviKrog(int k)
  {
    krog = k;
    init();
    for (int i = 1; i <= krog - 1; i++)
      premakni();

  }

  public int nasprotnik(int e)
  {
    for (int i = 1; i <= stEkip; i++)
      if (ekipa[i] == e)
        return(ekipa[stEkip + 1 - i]);
    
    return(-1);
  }

  public String razpored()
  {
    String s;
   
    s = "";
    for (int i = 1; i <= stEkip / 2; i++)
    { if (s.length() > 0) s = s + ",";
      s = s + Integer.toString(ekipa[i]) + "-" + Integer.toString(nasprotnik(ekipa[i]));
    }
    return(s);
  }

}