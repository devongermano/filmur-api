FROM gradle:jdk10
RUN ls -al
RUN cd ..
RUN ls -al
RUN gradle build
RUN ls -al
COPY build/libs/filmur-api-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar"]
