# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy only necessary files first
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn/

# Grant execute permissions to mvnw
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the project
COPY . ./

# Ensure mvnw is still executable
RUN chmod +x mvnw

# Build the application (skip tests)
RUN ./mvnw clean package -DskipTests

# Expose the application port (default Spring Boot port)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/todo_backend-0.0.1-SNAPSHOT.jar"]
