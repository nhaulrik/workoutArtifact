FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=target/workout-service-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]