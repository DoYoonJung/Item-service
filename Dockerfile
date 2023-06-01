FROM openjdk:8-jdk-alpine
COPY demo-0.0.1-SNAPSHOT myboot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/myboot.jar"]