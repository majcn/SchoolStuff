#
# Napisite skripto v bashu, ki izpise podano stevilo v obratnem vrstnem redu, npr. 123 izpise kot 321.
#

#! /bin/bash
if [ $# -ne 1 ]
then
  echo "Error" 1>&2
  exit 1
fi

echo "$1" | rev
