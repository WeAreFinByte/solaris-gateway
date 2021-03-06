#!/bin/bash

java "-Dspring.profiles.active=${SPRING_PROFILE_ENV}" \
            "-Dserver.port=${PORT_ENV}" \
            "-Dcom.finbyte.solarisgateway.auth.baseuri=${BASE_URI_ENV}" \
            "-Dcom.finbyte.solarisgateway.auth.uri=${URI_ENV}" \
            "-Dcom.finbyte.solarisgateway.auth.clientid=${CLIENT_ID_ENV}" \
            "-Dcom.finbyte.solarisgateway.auth.clientsecret=${CLIENT_SECRET_ENV}" \
            "-Dcom.finbyte.solarisgateway.auth.httpmethod=${HTTP_METHOD_ENV}" \
            "-Dcom.finbyte.solarisgateway.auth.granttype=${GRANT_TYPE_ENV}" \
            "-Dcom.finbyte.solarisgateway.auth.provider=${PROVIDER_ENV}" \
            "-Dcom.finbyte.solarisgateway.uri=${ROUTE_URI_ENV}" \
            "-Dcom.finbyte.solarisgateway.prefix=${PREFIX_ENV}" \
            "-Dcom.finbyte.solarisgateway.docker-secret.bind-path=${SECRET_PATH_ENV}" \
            "-Dcom.finbyte.solarisgateway.docker-secret.client-id=${CLIENT_ID_PATH_ENV}" \
            "-Dcom.finbyte.solarisgateway.docker-secret.client-secret=${CLIENT_SECRET_PATH_ENV}" \
            -jar /app.jar