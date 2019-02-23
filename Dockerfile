FROM gradle:4.5-jdk8-alpine as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:8-jre-alpine
WORKDIR /app
#EXPOSE 80
COPY --from=builder /builder/build/libs/filmur-api-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "filmur-api-0.0.1-SNAPSHOT.jar"]
