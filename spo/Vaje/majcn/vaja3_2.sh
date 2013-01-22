#
# Napisite skripto v bashu, ki izpise naslednji vzorec:
# 1
# 12
# 123
# 1234
# 12345
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
    echo -n "$j"
  done
  echo
done
