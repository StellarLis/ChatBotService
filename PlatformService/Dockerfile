FROM eclipse-temurin:21
WORKDIR /app
COPY build .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "libs/PlatformService-1.0-SNAPSHOT.jar"]
