FROM openjdk:8-jdk-alpine
MAINTAINER hyp
LABEL app="spring-boot-shiro-frame" version="1.0.0" by="hyp"
COPY ./target/spring-boot-shiro-frame-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Xms10m -Xmx128m","-jar","/app.jar"]