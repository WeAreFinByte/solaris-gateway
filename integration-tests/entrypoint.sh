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

wait-for-url

response=$(curl --write-out %{http_code} --silent --output /dev/null http://gateway:8090/uaa/header)

if [[ "$response" -ne 200 ]] ; then
  echo "Site status changed to $response"
  exit 1
else
  echo "OK STATUS $response"
  exit 0
fi