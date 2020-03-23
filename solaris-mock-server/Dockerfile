FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
ARG PORT=8081
ARG SPRING_PROFILE=dev

ENV PORT_ENV=$PORT
ENV SPRING_PROFILE_ENV=$SPRING_PROFILE

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILE_ENV}",\
            "-Dserver.port=${PORT_ENV}",\
            "-jar","/app.jar"]