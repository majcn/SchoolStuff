/*
Napisite C program, ki vsebino datoteke izpise v obratnem vrstnem redu v drugo datoteko. Imeni obeh datotek sta podani kot argumenta programa. Pri vsakem klicu preverite, ce se lahko izvede, sicer izpisite sporocilo in koncajte program. Delovati mora za poljubno dolge datoteke.
*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define BUFFSIZE 1

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

  lseek(inFile, -1, SEEK_END);

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
    if(lseek(inFile, -2, SEEK_CUR) < 0)
    {
      exit(0);
    }
  }
  return 0;
}
