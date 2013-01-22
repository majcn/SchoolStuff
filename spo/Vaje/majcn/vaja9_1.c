/*
Napisite C program, ki prvih 5 sekund lovi signal SIGINT (signal handler le nekaj izpise), drugih 5 sekund ga ignorira, tretjih 5 sekund pa izvaja privzeto akcijo za ta signal. Signal SIGINT posiljamo preko tipkovnice.
*/

#include <stdio.h>
#include <signal.h>

static void sig_int()
{
  printf("Ctrl+C pressed\n");
}

int main(int argc, char **argv)
{
  int st_sec = 5;

  if(signal(SIGINT, sig_int) == SIG_ERR)
    return -1;
  printf("Handle SIGINT\n");
  sleep(st_sec);

  if(signal(SIGINT, SIG_IGN) == SIG_ERR)
    return -1;
  printf("Ignore SIGINT\n");
  sleep(st_sec);

  if(signal(SIGINT, SIG_DFL) == SIG_ERR)
    return -1;
  printf("Default SIGINT\n");
  sleep(st_sec);

  return 0;
}
