FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
ARG SPRING_PROFILE=dev
ARG URI=http://localhost:8081
ARG CLIENT_ID=clientID
ARG CLIENT_SECRET=clientSecret
ARG PORT=8080

ENV SPRING_PROFILE_ENV=$SPRING_PROFILE
ENV URI_ENV=$URI
ENV CLIENT_ID_ENV=$CLIENT_ID
ENV CLIENT_SECRET_ENV=$CLIENT_SECRET
ENV PORT_ENV=$PORT

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILE_ENV}",\
            "-Dcom.finleap.solarisgateway.uri=${URI_ENV}",\
            "-Dserver.port=${PORT_ENV}",\
            "-Dcom.finleap.solarisgateway.clientid=${CLIENT_ID_ENV}",\
            "-Dcom.finleap.solarisgateway.clientsecret=${CLIENT_SECRET_ENV}",\
            "-jar","/app.jar"]