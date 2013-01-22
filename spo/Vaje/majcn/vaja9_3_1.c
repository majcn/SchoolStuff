/*
Napisite dva C programa: send in receive. Receive izpise svoj pid, sicer pa ne dela nic (prazna zanka), programu send pa vnesemo preko tipkovnice stevilko procesa od receive, da mu poslje SIGINT.
*/
//RECEIVE

#include <stdio.h>
#include <signal.h>

int main(int argc, char **argv)
{
  printf("%d\n", getpid());
  for(;;)
    pause();
  return 0;
}
