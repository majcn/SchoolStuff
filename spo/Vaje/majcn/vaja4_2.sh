# Napisite skripto v bashu, ki izpise naslednji vzorec:
# 
#        1
#       2 2
#      3 3 3
#     4 4 4 4
#    5 5 5 5 5
#   6 6 6 6 6 6
#  7 7 7 7 7 7 7
# 8 8 8 8 8 8 8 8  
#

#! /bin/bash
if [ $# -ne 1 ]
then
  echo "Error" 1>&2
  exit 1
fi
number="$1"
for((i=1; i<=number; i++)) do
  for((k=number-i; k>0; k--)) do
    echo -n " "
  done
  for((j=1; j<=i; j++)) do
    echo -n "$i "
  done
  echo
done
