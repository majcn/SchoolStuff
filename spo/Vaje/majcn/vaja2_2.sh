#
# Napišite skripto v bashu, ki poišče največje med tremi števili, podanimi kot argumenti ukazne vrstice. Izpišite napako, če stevilo argumentov ni pravo.
#

#! /bin/bash
if [ $# -ne 3 ]
then
  echo "Error" 1>&2
  exit 1
fi

if (( "$1" >= "$2" && "$1" >= "$3" ))
then
  echo "Prvi najvecji"
elif (( "$2" >= "$1" && "$2" >= "$3" ))
then
  echo "Drugi najvecji"
else
  echo "Tretji najvecji"
fi
