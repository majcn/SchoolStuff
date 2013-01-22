/*
Napisite 3 C programe. Prvi inicializira semafor na 0 (s semctl), drugi ga povecuje za 1, tretji pa ga zmanjsuje za 1. Vrednost sem_flg postavite na 0, kar pomeni cakanje.
*/
//POVECUJE

#include <stdio.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#define SEMKEYPATH "/file.ftok"  /* Path used on ftok for semget key  */
#define SEMKEYID 1              /* Id used on ftok for semget key    */

int main(int argc, char *argv[])
{
  int semid;
  key_t key; 
  struct sembuf operations[1];

  key = ftok(SEMKEYPATH, SEMKEYID);
  semid = semget(key, 1, 0666);
  
  operations[0].sem_num = 0;
  operations[0].sem_op = 1;
  operations[0].sem_flg = 0;
  semop(semid, operations, 1);
  return 0;
}
