#!/bin/bash
set -e

err_report() {
    echo "Error on line $1"
}

trap 'err_report $LINENO' ERR

wait-for-url() {
    while ! curl http://uaa:8080/healthz
    do
      echo "$(date) - still trying"
      sleep 1
    done
    echo "$(date) - connected successfully"
    sleep 5
}

urls=( http://gateway:8090/solaris/header http://gateway:8090/solaris/env/v1/persons http://gateway:8090/solaris/env/v1/accounts )
wait-for-url

for i in "${urls[@]}"
do
   :
   response=$(curl -H "Authorization: Basic c29sYXJpc1VzZXIxOnBhc3N3b3Jk" --write-out %{http_code} --silent --output /dev/null $i)

  if [[ "$response" -ne 200 ]] ; then
    echo "Site status changed to $response, for equest to $i"
    exit 1
  else
    echo "OK STATUS $response, successfully sent request to $i"
  fi
done
exit 0

