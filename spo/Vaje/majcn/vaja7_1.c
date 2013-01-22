/*
Napisite C program, ki prebere listo float stevil iz binarne datoteke.
Na zacetku datoteke je integer, ki pove, koliko float stevil sledi v datoteki.
Ime datoteke dobimo iz ukazne vrstice.
Ce je program klican brez argumentov, naj tvori binarno datoteko z imenom stevila s stevili 5(integer), 1.1, 2.2, 3.3, 4.4 in 5.5, ki jo bo potem lahko bral.
*/

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
  FILE *fp;

  int i;
  int n;
  float *st;

  if(argc > 1)
  {
    fp = fopen(argv[1], "r");
    if(fread(&n, sizeof(int), 1, fp) != 1)
    {
      printf("fread error");
      exit(1);
    }
    st = (float*)malloc(n*sizeof(float));
    if(fread(&st[0], sizeof(float), n, fp) != n)
    {
      printf("fread error");
      exit(1);
    }
    else
    {
      for(i=0; i<n; i++)
        printf("%f ", st[i]);
    }
  }
  else
  {
    fp = fopen("stevila", "w");
    n = 5;
    st = (float*)malloc(n*sizeof(float));
    st[0] = 1.1;
    st[1] = 2.2;
    st[2] = 3.3;
    st[3] = 4.4;
    st[4] = 5.5;
    if(fwrite(&n, sizeof(int), 1, fp) != 1)
    {
      printf("fwrite error");
      exit(1);
    }  
    if(fwrite(&st[0], sizeof(float), n, fp) != n)
    {
      printf("fwrite error");
      exit(1);
    }
  }

  fclose(fp);
  free(st);
  return 0;
}
