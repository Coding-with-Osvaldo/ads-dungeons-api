FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean
RUN mvn clean install

FROM openjdk:21
EXPOSE 8080
COPY --from=build /target/adsdungeons-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]