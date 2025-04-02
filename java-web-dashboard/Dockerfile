# Use a base image with both Maven and JDK support
FROM maven:3.8.5-openjdk-17

# Set the working directory
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the project and generate the JAR file
RUN mvn clean package

# Expose the application's port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "target/dashboard-1.0.0.jar"]
