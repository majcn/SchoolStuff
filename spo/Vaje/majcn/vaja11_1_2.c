/*Napisite 2 C programa. Prvi pise nize, ki jih vnasamo prek tipkovnice, na fifo.
Drugi nize bere z njega in jih izpisuje na zaslon.
(Prvi program uporablja funkcije mkfifo, open, gets in write.)
*/
//BERI

#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

#define BUFFSIZE 100

int main(int argc, char **argv)
{
  int fd, n;
  char beseda[BUFFSIZE];
  mkfifo("fifo", S_IFIFO | 0666);
  fd = open("fifo", O_RDONLY);

  while((n = read(fd, beseda, BUFFSIZE)) > 0)
  {
    write(STDOUT_FILENO, beseda, n);
    printf("\n");
  }

  return 0;
}
