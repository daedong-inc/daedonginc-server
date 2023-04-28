FROM openjdk:17

COPY daedonginc-interface/build/libs/*.jar app.jar
ARG PROFILE=prod
ENV PROFILE=${PROFILE}

ENTRYPOINT ["java","-Dspring.profiles-active=${PROFILE}","-jar","/app.jar"]