#
# Napisite skripto v bashu, ki izpise vsoto cifer, ki sestavljajo podano stevilo. Npr. za stevilo 123 izpise 6 (=1+2+3).
#

#! /bin/bash
if [ $# -ne 1 ]
then
  echo "Error" 1>&2
  exit 1
fi

stevilo="$1";
vsota=0;
while [ "$stevilo" -ne 0 ]
do
  ((vsota += stevilo%10))
  ((stevilo /= 10))
done
echo "$vsota"
