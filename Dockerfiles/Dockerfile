# Using Oracle GraalVM for JDK 17
FROM container-registry.oracle.com/graalvm/native-image:21-ol8 AS builder

# Set the working directory to /home/app
WORKDIR /build

# Copy the source code into the image for building
COPY . /build

# Add execute permission to mvnw script
RUN chmod +x mvnw

# Build
RUN ./mvnw --no-transfer-progress native:compile -Pnative

# Use a lightweight Linux base image with just enough runtime dependencies
FROM container-registry.oracle.com/graalvm/native-image:21-ol8

# Expose port 8080 for the application
EXPOSE 8080

# Copy the native executable into the containers
COPY --from=builder /build/target/gpt-4-copilot app

ENTRYPOINT ["/app"] 
