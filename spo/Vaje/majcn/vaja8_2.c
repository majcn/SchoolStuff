/*
Napisite C program, ki izpise prompt in izvaja vnesene ukaze (ki imajo lahko vec argumentov).
Celoten ukaz preberite v znakovni niz. Presledke zamenjajte z '\0', na zacetke podnizov pa naj kazejo
kazalci, ki jih boste podali kasneje exec funkciji.
Za posamezen ukaz naj proces s fork ustvari otroka, ki z eno od execv funkcij izvrsi ukaz.
(V primeru tezav implementirajte le verzijo, ki izvaja ukaze brez dodatnih argumentov.)
*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char **argv)
{
  int i;
  int stevec = 1;
  char *string = (char*)malloc(100*sizeof(char));
  char **para = (char**)malloc(1000*sizeof(char));
  int status;
  pid_t pid;

  while(1)
  {
    if((pid = fork()) == 0)
    {
      printf("%%");
      gets(string);
      for(i=0; i<100; i++)
      {
        if(string[i] == ' ')
        {
          para[stevec] = string + i + 1;
          string[i] = '\0';
          stevec++;
        }
      }
      para[0] = string;
      execvp(string, para);
      return 7;
    }
    else
      wait(&status);
  }
  return 0;
}
