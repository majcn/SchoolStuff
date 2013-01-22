/*
Napisite 3 C programe. Prvi inicializira semafor na 0 (s semctl), drugi ga povecuje za 1, tretji pa ga zmanjsuje za 1. Vrednost sem_flg postavite na 0, kar pomeni cakanje.
*/
//INICIALIZIRA

#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#define SEMKEYPATH "/file.ftok"  /* Path used on ftok for semget key  */
#define SEMKEYID 1              /* Id used on ftok for semget key    */

int main(int argc, char *argv[])
{
  union semun
  {
    int val;
    struct semid_ds *buf;
    unsigned short *array;
  } argument;
  argument.val = 0;

  int semid;
  int rc;
  key_t semkey;
  semkey = ftok(SEMKEYPATH,SEMKEYID);
  semid = semget( semkey, 1, 0666 | IPC_CREAT);
  rc = semctl( semid, 0, SETVAL, argument);
  return 0;
}
