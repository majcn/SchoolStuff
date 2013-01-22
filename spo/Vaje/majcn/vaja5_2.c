/*
Napisite verzijo programa cat s pomocjo read, write, open in close. Ce je program klican brez argumenta, kopira stdin v stdout. Lahko je podanih tudi vec argumentov (datotek).
*/

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define BUFFSIZE 512

int main(int argc, char **argv)
{
  int i = 0;
  int inFile;
  int n;
  char buffer[BUFFSIZE];

  if(argc==1)
  {
    while((n=read(STDIN_FILENO, buffer, BUFFSIZE))>0)
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
  }
  else
  {
    for(i=0; i<argc-1; i++)
    {
      inFile = open(argv[1], O_RDONLY);
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
      close(inFile);
    }
  }
  return 0;
}
