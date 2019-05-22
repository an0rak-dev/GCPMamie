FROM openjdk:8-stretch

WORKDIR /usr/src

EXPOSE 8080
COPY target/talk-gcp-mamie-1.0.0.jar /usr/src/ssd-back.jar
CMD ["java", "-jar", "ssd-back.jar"]
