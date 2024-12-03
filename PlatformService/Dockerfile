FROM openjdk:24-jdk
WORKDIR /app
COPY build .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "libs/PlatformService-1.0-SNAPSHOT.jar"]
