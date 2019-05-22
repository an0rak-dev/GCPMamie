FROM openjdk:8-stretch

COPY target/talk-gcp-mamie-1.0.0.jar /usr/src/ssd-back.jar
WORKDIR /usr/src

EXPOSE 8080
CMD ["java", "-jar", "ssd-back.jar"]
