#
# Napisite skripto v bashu, ki izrise naslednji vzorec:
# 
# |_
# | |_
# | | |_
# | | | |_
# | | | | |_
#

#! /bin/bash
if [ $# -ne 1 ]
then
  echo "Error" 1>&2
  exit 1
fi

stevilo="$1";
for((i=1; i<=stevilo; i++)) do
  for((j=1; j<=i; j++)) do
    echo -n "|"
  if(("$i" == "$j"))
  then
    echo -n "_"
  else
    echo -n " "
  fi
  done
  echo
done
