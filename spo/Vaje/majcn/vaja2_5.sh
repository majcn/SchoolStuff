#
# Napišite skripto v bashu, ki ugotovi, ali podana datoteka obstaja ali ne. Ime datoteke je podano kot argument ukazne vrstice. Preverite tudi število argumentov.
#

#! /bin/bash
if [ $# -ne 1 ]
then
  echo "Error" 1>&2
  exit 1
fi

if [ -f "$1" ]
then
  echo "Datoteka obstaja"
else
  echo "Datoteka ne obstaja"
fi
