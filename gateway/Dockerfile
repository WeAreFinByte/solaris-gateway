FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
ARG SPRING_PROFILE=dev
ARG BASE_URI=http://uaa:8080
ARG URI=/oauth/token
ARG ROUTE_URI=http://resource-server:8081
ARG CLIENT_ID=clientID
ARG CLIENT_SECRET=clientSecret
ARG PORT=8090
ARG HTTP_METHOD=POST
ARG PROVIDER=uaa
ARG GRANT_TYPE=client_credentials

ENV SPRING_PROFILE_ENV=$SPRING_PROFILE
ENV BASE_URI_ENV=$BASE_URI
ENV URI_ENV=$URI
ENV ROUTE_URI_ENV=$ROUTE_URI
ENV CLIENT_ID_ENV=$CLIENT_ID
ENV CLIENT_SECRET_ENV=$CLIENT_SECRET
ENV PORT_ENV=$PORT
ENV HTTP_METHOD_ENV=$HTTP_METHOD
ENV PROVIDER_ENV=$PROVIDER
ENV GRANT_TYPE_ENV=$GRANT_TYPE

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILE_ENV}",\
            "-Dserver.port=${PORT_ENV}",\
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.baseuri=${BASE_URI_ENV}",\
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.uri=${URI_ENV}",\
            "-Dcom.finleap.solarisgateway.uri=${ROUTE_URI_ENV}",\
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.clientid=${CLIENT_ID_ENV}",\
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.clientsecret=${CLIENT_SECRET_ENV}",\
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.httpmethod=${HTTP_METHOD_ENV}",\
            "-Dcom.finleap.solarisgateway.auth.${PROVIDER_ENV}.granttype=${GRANT_TYPE_ENV}",\
            "-jar","/app.jar"]