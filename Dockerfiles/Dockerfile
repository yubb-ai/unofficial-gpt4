# Using Oracle GraalVM for JDK 17
FROM devatherock/graalvm:21-ol8 AS builder

# Set the working directory to /home/app
WORKDIR /build

# Copy the source code into the image for building
COPY . /build

# Add execute permission to mvnw script
RUN chmod +x mvnw

# Build
RUN chmod +x mvnw && \
    ./mvnw --no-transfer-progress native:compile -Pnative && \
    rm -rf /root/.m2
    
# Use a lightweight Linux base image with just enough runtime dependencies
FROM ubuntu:latest

# Copy the native executable into the containers
COPY --from=builder /build/target/gpt-4-copilot gpt-4-copilot

ENTRYPOINT ["/gpt-4-copilot"] 
