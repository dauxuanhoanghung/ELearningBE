# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk as base

# Set the working directory in the container
WORKDIR /app
# Copy whole the need files, folders to run
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Development Stage: docker compose -f compose.dev.yml up --build
FROM base as development
COPY src ./src
COPY tokens/ tokens
CMD ["./mvnw", "spring-boot:run"]

# Build Stage
FROM base as build
RUN ./mvnw package

# Production Stage
FROM openjdk:17-jdk-alpine as production

# Set the working directory in the container
WORKDIR /app
# Expose the port that the application will run ona
EXPOSE ${SERVER_PORT:-8080}
# Copy the tokens folder from the host to the container during build
COPY tokens/ tokens
# Copy the executable JAR file from the host to the container
COPY --from=build /app/target/demo-*.jar demo-production.jar

# Define the command to run the application
CMD ["java", "-jar", "demo-production.jar"]
 
