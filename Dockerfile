FROM openjdk:8-jdk-alpine
LABEL maintainer="Nikolaj Haulrik <nikolaj.haulrik@gmail.com>"
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]