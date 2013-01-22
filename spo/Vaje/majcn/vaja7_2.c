/*
Napisite v C-ju poenostavljeno verzijo ukaza ls -l, ki izpise imena vseh datotek v tekocem direktoriju, kakor tudi tip datoteke, mode (dostopna dovoljenja) in stevilo povezav. (Pri tem ne smete uporabiti kar exec ls -l.) Primer izpisa:

-rw-r--r-- 1 myls.c
drwxr-xr-x 42 ..
-rw-r--r-- 1 myls.c~
-rwxr-xr-x 1 a.out
drwxr-xr-x 2 .
*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>

int main(int argc, char **argv)
{
  DIR *dip = opendir(".");
  struct dirent *dit;
  struct stat st;
  char mode;

  while((dit = readdir(dip)) != NULL)
  {
    stat(dit->d_name, &st);
    if(S_ISDIR(st.st_mode))
      mode = 'd';
    else if(S_ISLNK(st.st_mode))
      mode = 'l';
    else if(S_ISCHR(st.st_mode))
      mode = 'c';
    else if(S_ISBLK(st.st_mode))
      mode = 'b';
    else if(S_ISFIFO(st.st_mode))
      mode = '|';
    else
      mode = '-';
    printf("%c%c%c%c%c%c%c%c%c%c %d %s\n", mode
                                         , st.st_mode & S_IRUSR ? 'r':'-'
                                         , st.st_mode & S_IWUSR ? 'w':'-'
                                         , st.st_mode & S_IXUSR ? 'x':'-'
                                         , st.st_mode & S_IRGRP ? 'r':'-'
                                         , st.st_mode & S_IWGRP ? 'w':'-'
                                         , st.st_mode & S_IXGRP ? 'x':'-'
                                         , st.st_mode & S_IROTH ? 'r':'-'
                                         , st.st_mode & S_IWOTH ? 'w':'-'
                                         , st.st_mode & S_IXOTH ? 'x':'-'
                                         , st.st_nlink
                                         , dit->d_name);
  }
  return 0;
}
