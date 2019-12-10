FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD
MAINTAINER Brian Hannaway

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/workout-service-0.0.1.jar /app/
ENTRYPOINT ["java", "-jar", "workout-service-0.0.1.jar"]
EXPOSE 9090

# docker container run --network="host" workout-service.