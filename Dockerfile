FROM openjdk:8-jdk-alpine
LABEL maintainer="Nikolaj Haulrik <nikolaj.haulrik@gmail.com>"
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]

#docker run --rm -p 9090:9090 f81d4f21ac7a