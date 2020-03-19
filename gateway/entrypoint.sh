#!/bin/bash

java "-Dspring.profiles.active=${SPRING_PROFILE_ENV}" \
            "-Dserver.port=${PORT_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.baseuri=${BASE_URI_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.uri=${URI_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.clientid=${CLIENT_ID_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.clientsecret=${CLIENT_SECRET_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.httpmethod=${HTTP_METHOD_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.granttype=${GRANT_TYPE_ENV}" \
            "-Dcom.finleap.solarisgateway.uri=${ROUTE_URI_ENV}" \
            "-Dcom.finleap.solarisgateway.prefix=${PREFIX_ENV}" \
            "-Dcom.finleap.solarisgateway.docker-secret.bind-path=${SECRET_PATH_ENV}" \
            "-Dcom.finleap.solarisgateway.docker-secret.client-id=${CLIENT_ID_PATH_ENV}" \
            "-Dcom.finleap.solarisgateway.docker-secret.client-secret=${CLIENT_SECRET_PATH_ENV}" \
            -jar /app.jar