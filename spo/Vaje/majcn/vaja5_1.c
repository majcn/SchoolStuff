/*
Napisite program v C-ju, ki kopira eno datoteko v drugo. Imeni obeh datotek sta podani kot argumenta programu. Kopirajte datoteko po en blok (512 bytov) naenkrat.
Preverite:
- da ima program 2 argumenta
- da je prva datoteka berljiva
- da je druga datoteka zapisljiva
sicer izpisite ustrezno sporocilo in zakljucite program.
*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define BUFFSIZE 512

int main(int argc, char **argv)
{
  if(argc!=3)
  {
    printf("Wrong nr. of parameters\n");
    exit(1);
  }

  char buffer[BUFFSIZE];
  int inFile = open(argv[1], O_RDONLY);
  int outFile = open(argv[2], O_RDWR | O_CREAT | O_TRUNC);
  int n;

  while((n=read(inFile, buffer, BUFFSIZE))>0)
  {
    if(write(outFile, buffer, n) != n)
    {
      printf("Can't write\n");
      exit(1);
    }
    if(n<0)
    {
      printf("Can't read\n");
      exit(1);
    }
  }
  return 0;
}
