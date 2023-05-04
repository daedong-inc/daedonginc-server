FROM openjdk:17

COPY daedonginc-interface/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]