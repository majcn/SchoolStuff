/*
Napisite 2 C programa. Prvi pise nize, ki jih vnasamo prek tipkovnice, na fifo.
Drugi nize bere z njega in jih izpisuje na zaslon.
(Prvi program uporablja funkcije mkfifo, open, gets in write.)
*/
//PISI

#include <stdio.h>
#include <fcntl.h>

int main(int argc, char **argv)
{
  int fd;
  char beseda[100];
  fd = open("fifo", O_WRONLY);
  for(;;)
  {
    gets(beseda);
    write(fd, beseda, strlen(beseda));
  }
  return 0;
}
