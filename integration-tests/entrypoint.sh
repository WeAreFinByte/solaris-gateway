#!/bin/bash
set -e

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

response=$(curl --write-out %{http_code} --silent --output /dev/null http://gatewasdasay:8090/uaa/header)

if [[ "$response" -ne 200 ]] ; then
  echo "Site status changed to $response"
  exit 1
else
  echo "OK STATUS $response"
  exit 0
fi