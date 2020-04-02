#!/bin/bash
set -e

err_report() {
    echo "Error on line $1"
}

wait-for-url() {
    while ! curl http://gateway:8090/actuator/health -H "Authorization: Basic YWRtaW46cGFzc3dvcmQ="
    do
      echo "$(date) - still trying"
      sleep 1
    done
    echo "$(date) - connected successfully"
    sleep 5
}

trap 'err_report $LINENO' ERR

urls=( http://gateway:8090/solaris/persons http://gateway:8090/solaris/accounts )
wait-for-url
sleep 15

for i in "${urls[@]}"
do
   :
   response=$(curl -H "Authorization: Basic cGF5bWVudFVzZXIxOnBhc3N3b3Jk" --write-out %{http_code} --silent --output /dev/null $i)

  if [[ "$response" -ne 200 ]] ; then
    echo "Site status changed to $response, for equest to $i"
    exit 1
  else
    echo "OK STATUS $response, successfully sent request to $i"
  fi
done
exit 0

