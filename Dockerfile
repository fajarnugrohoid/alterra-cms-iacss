FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY ${JAR_FILE} springboot-iacss-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","springboot-iacss-0.0.1-SNAPSHOT.jar"]