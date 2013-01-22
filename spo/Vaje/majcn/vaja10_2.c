/*
Napisite C program, ki s pomocjo cevi izvede ukaz ps -ef | grep <user>.
Stars naj izvede ps -ef, otrok pa grep <user>, pri cemer je <user> vase uporabnisko ime.
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
  char username[MAXLINE];
  FILE *fp;

  pipe(fd);

  if((pid = fork()) == 0)
  {
    close(fd[1]);
    //start get username
    fp = popen("whoami", "r");
    fgets(username, MAXLINE, fp);
    pclose(fp);
    //end get username
    dup2(fd[0], STDIN_FILENO);
    close(fd[0]);
    execl("/bin/grep", "grep", username,(char *) 0);
  }
  else
  {
    close(fd[0]);
    fp = popen("ps -ef", "r");
    while (fgets(line, MAXLINE, fp) != NULL)
    {
      n = strlen(line);
      write(fd[1], line, n);
    }
    close(fd[1]);
  }
  return 0;
}
