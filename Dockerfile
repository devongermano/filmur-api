FROM gradle:jdk10 as builder
RUN gradle build
VOLUME /tmp
#ARG JAR_FILE
COPY build/libs/ccfilmur-api-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar"]
