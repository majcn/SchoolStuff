#
# Napišite skripto, ki vzame listo datotek kot vhod in izpiše basename vsake datoteke v listi. Uporabite while in shift.
#

#! /bin/bash
while [ "$#" -ne 0 ]
do
  echo "$(basename $1)"
  shift
done
