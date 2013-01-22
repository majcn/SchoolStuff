/*
Napisite dva C programa: send in receive. Receive izpise svoj pid, sicer pa ne dela nic (prazna zanka), programu send pa vnesemo preko tipkovnice stevilko procesa od receive, da mu poslje SIGINT.
*/
//SEND

#include <stdio.h>
#include <signal.h>

int main(int argc, char **argv)
{
  int pid;
  scanf("%d", &pid);
  kill(pid, SIGINT);
  return 0;
}
