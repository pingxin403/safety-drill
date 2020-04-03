FROM openjdk:8-jdk-alpine
MAINTAINER hyp
LABEL app="company-frame" version="0.0.1" by="hyp"
COPY ./target/company-frame-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Xms10m -Xmx128m","-jar","/app.jar"]