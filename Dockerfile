FROM openjdk:8
VOLUME /tmp
EXPOSE 9292
ADD ./target/microservice-Atm-0.0.1-SNAPSHOT.jar service-atm.jar
ENTRYPOINT ["java","-jar","/service-atm.jar"]