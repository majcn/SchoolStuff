#
# Napisite skripto v bashu s pomocjo case stavka, ki izvaja osnovne matematicne operacije (+,-,x,/). Poklicemo jo kot npr. ./ime 20 / 3
# Preverite tudi pravo stevilo argumentov ukazne vrstice.
#

#! /bin/bash
if [ $# -ne 3 ]
then
  echo "Error" 1>&2
  exit 1
fi

case "$2" in
'/') echo "$(($1/$3))";;
'x') echo "$(($1*$3))";;
'+') echo "$(($1+$3))";;
'-') echo "$(($1-$3))";;
esac
