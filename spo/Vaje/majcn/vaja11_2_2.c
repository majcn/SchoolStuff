/*
Napisite 2 C programa. Prvi pise nize, ki jih vnasamo prek tipkovnice, v sporocilno vrsto.
Drugi nize bere z nje in jih izpisuje na zaslon.
Status sporocilne vrste lahko pogledamo z ukazom ipcs.
*/
//BERI

#include <stdio.h>
#include<stdlib.h>
#include<errno.h>
#include<string.h>
#include<sys/types.h>
#include<sys/ipc.h>
#include<sys/msg.h>


#define BUFFSIZE 100

struct my_msgbuf
{
  long mtype;
  char mtext[BUFFSIZE];
};

int main(int argc, char **argv)
{
  struct my_msgbuf buf;
  int msqid;
  int len;
  key_t key;

  key = ftok("vaja11_2_1.c", 'A');
  msqid = msgget(key, 0644);

  buf.mtype = 1;
  for(;;)
  {
    msgrcv(msqid, &buf, BUFFSIZE, 0, 0);
    printf("%s", buf.mtext);
  }
  return 0;
}
