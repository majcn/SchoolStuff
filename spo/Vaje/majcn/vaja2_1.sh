#
# Napišite skripto v bashu, ki sešteje dve stevili, ki sta podani kot argumenta ukazne vrstice. Če števili nista podani, izpišite napako in uporabo programa.
#

#! /bin/bash
if [ $# -ne 2 ]
then
  echo "Error" 1>&2
  exit 1
fi

((number = $1 + $2))
echo "$number"
