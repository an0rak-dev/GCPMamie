FROM openjdk:8-stretch

WORKDIR /usr/src
RUN  mkdir /opt/cdbg |  \
     wget -qO- https://storage.googleapis.com/cloud-debugger/compute-java/debian-wheezy/cdbg_java_agent_gce.tar.gz | \
     tar xvz -C /opt/cdbg

EXPOSE 8080
COPY target/talk-gcp-mamie-1.0.0.jar /usr/src/ssd-back.jar
ENTRYPOINT ["java", "-agentpath:/opt/cdbg/cdbg_java_agent.so", "-Dcom.google.cdbg.module=ssd-back", "-Dcom.google.cdbg.version=1.0.0", "-jar", "ssd-back.jar"]
