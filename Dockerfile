# Use an official OpenJDK runtime as a parent image
FROM gradle:jdk17-focal AS builder
WORKDIR /workspace
COPY . /workspace
RUN gradle build -x test

FROM eclipse-temurin:17-jre-focal
ENV SPRING_PROFILES_ACTIVE=container

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the GitHub Action artifact
COPY --from=builder /workspace/build/libs/credential-store.jar /app/credential-store.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/tenant-service.jar"]