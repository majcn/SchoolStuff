/*
Napisite C program, v katerem se procesu starsu generirajo otrok, vnuk in pravnuk.
Vsak naj izpise pid svojega starsa in svoj pid. Nato naj spi 3 sekunde, preden generira potomca.
Vsak od starsev naj pocaka na svojega otroka (z wait oz. waitpid) in ugotovi ter izpise njegov izhodni status.
*/

#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

void pr_exit(int status)
{
  if (WIFEXITED(status))
    printf("normal termination, exit status = %d\n", WEXITSTATUS(status));
  else if (WIFSIGNALED(status))
    printf("abnormal termination, signal number = %d%s\n", WTERMSIG(status),
      #ifdef WCOREDUMP
        WCOREDUMP(status) ? " (core file generated)" : "");
      #else
        "");
      #endif
  else if (WIFSTOPPED(status))
    printf("child stopped, signal number = %d\n", WSTOPSIG(status));
}

int main(int argc, char **argv)
{
  pid_t pid;
  int status;

  if((pid = fork()) == 0)
  {
    printf("Stars pid: %d\t Moj pid: %d\n", getppid(), getpid());
    sleep(3);
    if((pid = fork()) == 0)
    {
      printf("Stars pid: %d\t Moj pid: %d\n", getppid(), getpid());
      sleep(3);
      if((pid = fork()) == 0)
      {
        printf("Stars pid: %d\t Moj pid: %d\n", getppid(), getpid());
        sleep(3);
        return 7;
      }
      else
      {
        wait(&status);
        pr_exit(status);
      }
      return 7;
    }
    else
    {
      wait(&status);
      pr_exit(status);
    }
    return 7;
  }
  else
  {
    wait(&status);
    pr_exit(status);
  }

  return 0;
}
