FROM eclipse-temurin:21-jdk-alpine
LABEL authors="ArulanandhaGuru"

WORKDIR /app

COPY target/service-discovery-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9000

ENTRYPOINT ["java","-jar","app.jar"]