# Use a base image with Java runtime (Alpine is a popular lightweight one made for containers)
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the working directory
COPY target/P2Demo-1.0.0.jar app.jar

# Expose the port that the Spring application listens on
EXPOSE 4444

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]