#
# Napišite skripto v bashu, ki izpiše števila 5, 4, 3, 2, 1 z while zanko.
#

#! /bin/bash
number=5
while [ "$number" -gt 0 ]
do
  echo -n "$number "
  ((number -= 1))
done
echo
