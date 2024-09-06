# BUILD STAGE
# Use a Gradle image with JDK to compile and package the Spring Boot application
FROM gradle:7.6.0-jdk19 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files and build.gradle file first to take advantage of Docker caching
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies (caches Gradle dependencies)
RUN ./gradlew build --no-daemon -x test

# Copy the entire project source code to the container
COPY src ./src

# Build the application without running tests to speed up the process
RUN ./gradlew clean build -x test --no-daemon

# RUN STAGE
# Use a lightweight JRE image to run the packaged application
FROM eclipse-temurin:22.0.1_8-jre-ubi9-minimal

# Set the working directory inside the container
WORKDIR /app

# Copy the generated JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the Spring Boot application runs on
EXPOSE 8081

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
