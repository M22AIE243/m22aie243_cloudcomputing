# Use a minimal Linux image as the base
FROM alpine:latest

LABEL maintainer="M22AIE243"
LABEL email="M22AIE243@iitj.ac.in"
LABEL  version=1.0

# Set environment variables for Java
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

# Install OpenJDK 11
RUN apk add --no-cache openjdk11

# Set the working directory
WORKDIR /app

# Copy your Spring Boot application JAR file into the container
COPY  app.jar .

RUN chmod +x app.jar

# Expose the port your Spring Boot application listens on (e.g., 8080)
EXPOSE 8080

# Command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
