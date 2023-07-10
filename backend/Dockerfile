# Build stage
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copy the backend source code to the container
COPY /cardiscover-backend .

# Build the backend application using Maven
RUN mvn clean package -DskipTests

# Production stage
FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build  /app/target/cardiscover-0.0.1-SNAPSHOT.jar .

# Expose the backend application's port
EXPOSE 8080

# Run the backend application
CMD ["java", "-jar", "cardiscover-0.0.1-SNAPSHOT.jar"]

