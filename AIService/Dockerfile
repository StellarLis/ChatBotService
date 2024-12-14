FROM eclipse-temurin:21
WORKDIR /app
COPY build .
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "libs/AIService-1.0-SNAPSHOT.jar"]