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

endpoint="http://gateway:8090/uaa/uaa/header"
wait-for-url

response=$(curl -H "Authorization: Basic c29sYXJpc1VzZXIxOnBhc3N3b3Jk" --write-out %{http_code} --silent --output /dev/null $endpoint)

if [[ "$response" -ne 200 ]] ; then
  echo "Site status changed to $response"
  exit 1
else
  echo "OK STATUS $response, successfully sent request to $endpoint"
  exit 0
fi