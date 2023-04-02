# Base Image
FROM openjdk:11-jdk

# Environment variables
ENV APP_HOME=/app

# Create app folder
RUN mkdir $APP_HOME
WORKDIR $APP_HOME

# Copy the application files
COPY . .

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package

# Expose Ports
EXPOSE 8080
EXPOSE 6379

# Install Redis
RUN apt-get update && apt-get install -y redis-server

# Start Redis and Spring App
CMD redis-server & java -jar target/demo-0.0.1-SNAPSHOT.jar
