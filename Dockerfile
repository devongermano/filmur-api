FROM gradle:jdk10
VOLUME /tmp
RUN gradle build
RUN ls -al
COPY build/libs/filmur-api-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar"]
