# Use Java 17 as the base image
FROM eclipse-temurin:17.0.7_7-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Java Spring Boot application into the container
COPY build/libs/mindstamp-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "mindstamp-0.0.1-SNAPSHOT.jar"]

# Expose port 8080 for the application
EXPOSE 8080

# Set the entry point to run the Java application
