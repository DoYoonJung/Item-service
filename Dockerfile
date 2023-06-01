FROM openjdk:8-jdk-alpine
ARG JAR_FILE=demo-0.0.1-SNAPSHOT
COPY ${JAR_FILE} myboot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/myboot.jar"]