#
# Napisite skripto v bashu, ki rekurzivno izpise vse mape in datoteke v podani mapi (in njenih podmapah). Nasvet: uporabite rekurzivno funkcijo.
#

#! /bin/bash
funkcija()
{
if [ $# -ne 1 ]
then
  echo "Error" 1>&2
  exit 1
fi

a=`ls $1`
for i in $a
do
  echo "$i"
  if [ -d $i ]
  then
    funkcija $i;
  fi
done
}

funkcija $1;
