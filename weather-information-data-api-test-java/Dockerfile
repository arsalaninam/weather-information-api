# Base Image
FROM openjdk:11-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and src directories to the container
COPY pom.xml .
COPY src/ ./src/
COPY testng.xml .

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn install -DskipTests

# Copy the built JAR file and results to the working directory
COPY target/ .

#E Entry point
CMD ["mvn", "clean", "package"]