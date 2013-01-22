/*
Napisite C program, ki izpise zadnjih n vrstic tekstovne datoteke. Program klicemo kot npr. "zadnje -n file.txt". Privzeta vrednost za n je 5.
*/

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define BUFFSIZE 1

int main(int argc, char **argv)
{
  if(argc<=2 && argc>=3)
  {
    printf("Wrong nr. of parameters\n");
    exit(1);
  }

  char buffer[BUFFSIZE];
  int inFile; 
  int n;
  int st;

  if(argc == 3)
  {
    st = -atoi(argv[1]);
    inFile = open(argv[2], O_RDONLY);
  }
  else
  {
    st = 5;
    inFile = open(argv[1], O_RDONLY);
  }
    

  lseek(inFile, -1, SEEK_END);

  while((n=read(inFile, buffer, BUFFSIZE))>0)
  {
    if(st==0)
      break;
    if(buffer[0] == '\n')
      st--;
    if(n<0)
    {
      printf("Can't read\n");
      exit(1);
    }
    if(lseek(inFile, -2, SEEK_CUR) < 0)
    {
      break;
    }
  }

  while((n=read(inFile, buffer, BUFFSIZE))>0)
  {
    if(write(STDOUT_FILENO, buffer, n) != n)
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
