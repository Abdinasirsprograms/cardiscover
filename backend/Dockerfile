# Build stage
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copy the backend source code to the container
COPY /cardiscover-backend .

# Build the backend application using Maven
RUN mvn clean package -DskipTests
mvn clean package -Dmaven.test.skip=true
# 
# mvn install -Dmaven.test.skip=true


# If you absolutely must, you can also use the maven.test.skip property to skip compiling the tests. maven.test.skip is honored by Surefire, 
# Failsafe and the Compiler Plugin.
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build  /app/target/cardiscover-0.0.1-SNAPSHOT.jar .

# Expose the backend application's port
EXPOSE 8080

# Run the backend application
CMD ["java", "-jar", "cardiscover-0.0.1-SNAPSHOT.jar"]

