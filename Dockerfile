# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file (pom.xml) and dependencies first to leverage Docker caching
COPY pom.xml ./
COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn .mvn/

# Grant execute permissions to mvnw
RUN chmod +x mvnw

# Download dependencies (use this step to cache dependencies)
RUN ./mvnw dependency:go-offline

# Copy the entire project
COPY . ./

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the application port (change this if your app runs on a different port)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/todo_backend-0.0.1-SNAPSHOT.jar"]
