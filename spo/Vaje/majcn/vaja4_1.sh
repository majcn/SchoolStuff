#
# Napisite skripto v bashu, ki izpise vsebino datoteke od podane stevilke vrstice
# naprej, podano stevilo vrstic. Npr., ./ime 4 5 myf izpise 5 vrstic datoteke myf od vrstice 4 naprej.
#

#! /bin/bash
if [ $# -ne 3 ]
then
  echo "Error" 1>&2
  exit 1
fi

((sum = $1+$2-1));
head -n "$sum" "$3" | tail -n "$2"
