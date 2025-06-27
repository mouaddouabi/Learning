# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
# Assumes your JAR is in the target/ directory
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]