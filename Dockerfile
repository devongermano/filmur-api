FROM openjdk:8-jdk-alpine
RUN ./gradlew build
VOLUME /tmp
#ARG JAR_FILE
COPY build/libs/filmur-api-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar"]
