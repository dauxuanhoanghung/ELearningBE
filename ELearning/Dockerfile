# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk as base

# Set the working directory in the container
WORKDIR /app
# Copy whole the need files, folders to run
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
COPY tokens/ tokens

# Development Stage: docker compose -f compose.dev.yml up --build
FROM base as development
CMD ["./mvnw", "spring-boot:run"]

# Build Stage
FROM base as builder
RUN ./mvnw package -Dmaven.test.skip

# Production Stage
FROM openjdk:17-jdk as production
WORKDIR /app

# Copy the executable JAR file from the builder to the container
COPY --from=builder /app/target/ELearning-*.jar production.jar

# Copy the tokens folder from the host to the container during build
COPY tokens/ tokens

# Define the command to run the application
CMD ["java", "-jar", "production.jar"]
 
