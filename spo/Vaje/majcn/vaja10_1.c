/*
Napisite C program, pri katerem otrok na std. izhod izpise tri kratka sporocila.
Pred tem z dup2 preusmerite std. izhod na pisalni konec cevi.
Starsu bralni konec cevi predstavlja std. vhod. Stars naj izpise, kar je dobil po cevi.
*/

#include <stdio.h>
#include <unistd.h>

#define MAXLINE 255

int main(int argc, char **argv)
{
  int fd[2];
  pid_t pid;
  int n;
  char line[MAXLINE];

  pipe(fd);

  if((pid = fork()) == 0)
  {
    close(fd[0]);
    dup2(fd[1], STDOUT_FILENO);
    close(fd[1]);
    printf("1. Sporocilo\n");
    printf("2. Sporocilo\n");
    printf("3. Sporocilo\n");
  }
  else
  {
    close(fd[1]);
    n = read(fd[0], line, MAXLINE);
    write(STDOUT_FILENO, line, n);
    close(fd[0]);
  }
  return 0;
}
