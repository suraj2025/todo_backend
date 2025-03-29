# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy only necessary files first (to leverage Docker cache)
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn/

# Grant execute permissions to the mvnw script
RUN chmod +x mvnw

# Download dependencies (cache dependencies)
RUN ./mvnw dependency:go-offline

# Copy the rest of the application source
COPY . ./

# Ensure mvnw has execute permissions after copying everything
RUN chmod +x mvnw

# Build the application (skip tests)
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/todo_backend-0.0.1-SNAPSHOT.jar"]
