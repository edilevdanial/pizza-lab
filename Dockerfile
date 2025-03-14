FROM eclipse-temurin:17-jdk-alpine as build

# Set working directory in container
WORKDIR /app

# Copy Gradle build files and application source code
COPY . .

# Build the application (Adjust this based on your actual build command)
RUN ./gradlew clean build -x test

# Create a new stage for the final image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the previous build stage
COPY --from=0 /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
