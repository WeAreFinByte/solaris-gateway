#!/bin/bash

java "-Dspring.profiles.active=${SPRING_PROFILE_ENV}" \
            "-Dserver.port=${PORT_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.baseuri=${BASE_URI_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.uri=${URI_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.clientid=${CLIENT_ID_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.clientsecret=${CLIENT_SECRET_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.httpmethod=${HTTP_METHOD_ENV}" \
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.granttype=${GRANT_TYPE_ENV}" \
            "-Dcom.finleap.solarisgateway.uri=${ROUTE_URI_ENV}" \
            -jar /app.jar