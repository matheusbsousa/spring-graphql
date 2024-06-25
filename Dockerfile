FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/boat-rental-service.jar /app

# Expose the port that your Spring Boot application uses (default is 8080)
EXPOSE 8080

# Set default Spring profile to dev
ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java", "-jar", "boat-rental-service.jar"]
