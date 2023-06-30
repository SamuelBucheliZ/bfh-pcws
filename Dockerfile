FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
COPY target/pcws.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]