public class Datum
{
  private int dan, mesec, leto;

  public Datum()
  {
    }

  public void nastavi(int d, int m, int l)
  {
    dan = d;
    mesec = m;
    leto = l;
  }

  public int dniVMesecu()
  { int dni;
    switch(mesec)
    {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        dni = 31;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        dni = 30;
        break;
      case 2:
        if (leto % 4 == 0 && leto % 100 != 0) dni = 29;
          else dni = 28;
        break;
      default:
        dni = 0;
        break;
    }
    return(dni);
  }

  public void dodajDneve(int k)
  {
    int dvm;

    dvm = dniVMesecu();
    
    while (dan + k > dvm)
    {
      k -= dvm - dan + 1;
      dan = 1;
      mesec++;
      if (mesec > 12)
      {
        mesec = 1;
        leto++;
      }
    }

    dan += k;
  }

  public int vrniDan()
  {  return(dan);
  }

  public int vrniMesec()
  { return(mesec);
  }
  
  public int vrniLeto()
  {
    return(leto);
  }

}