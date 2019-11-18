FROM adoptopenjdk/maven-openjdk11:latest AS MAVEN_BUILD
COPY pom.xml /tmp/
COPY src /tmp/src
WORKDIR /tmp
RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /tmp/target/ff-*.*.*-SNAPSHOT.jar /app/ff.jar
CMD ["java", "-jar", "ff.jar"]
