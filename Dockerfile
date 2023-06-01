FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/*.jar
COPY ${JAR_FILE} myboot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/myboot.jar"]