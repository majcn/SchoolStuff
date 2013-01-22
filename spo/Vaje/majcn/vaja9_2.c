/*
Napisite C program, v katerem v razmaku 5 sekund stars otroku zaporedoma poslje signale SIGBUS, SIGPIPE in SIGQUIT, otrok pa naj jih ujame in to izpise.
*/

#include <stdio.h>
#include <signal.h>

static void sig_bus()
{
  printf("SIGBUS handle\n");
}

static void sig_pipe()
{
  printf("SIGPIPE handle\n");
}

static void sig_quit()
{
  printf("SIGQUIT handle\n");
}

int main(int argc, char **argv)
{
  int st_sec = 5;
  pid_t pid;

  if((pid = fork()) == 0)
  {
    if(signal(SIGBUS, sig_bus) == SIG_ERR)
      return -1;
    if(signal(SIGPIPE, sig_pipe) == SIG_ERR)
      return -1;
    if(signal(SIGQUIT, sig_quit) == SIG_ERR)
      return -1;
    for(;;)
      pause();
  }
  else
  {
    sleep(st_sec);
    kill(pid, SIGBUS);
    sleep(st_sec);
    kill(pid, SIGPIPE);
    sleep(st_sec);
    kill(pid, SIGQUIT);
  }
  return 0;
}
