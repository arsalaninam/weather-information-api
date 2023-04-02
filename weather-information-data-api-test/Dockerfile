# Base Image
FROM python:3.8-slim-buster

# Environment variables
ENV APP_HOME=/app

# Create app folder
RUN mkdir $APP_HOME
WORKDIR $APP_HOME

# Install dependencies
RUN pip install -U pip && pip install pytest requests jsonschema

# Copy test files
COPY . .

# Start pytest
CMD pytest
