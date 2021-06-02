FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
COPY src/main/resources/import.sql import.sql

ENTRYPOINT ["java","-jar","/app.jar"]