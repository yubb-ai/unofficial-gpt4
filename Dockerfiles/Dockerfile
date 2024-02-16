# Using Oracle GraalVM for JDK 17
FROM observabilitystack/graalvm-maven-builder:21.0.1-ol9 AS builder

# Set the working directory to /home/app
WORKDIR /build

# Copy the source code into the image for building
COPY . /build

# Add execute permission to mvnw script
RUN chmod +x mvnw

# Build
RUN ./mvnw --no-transfer-progress native:compile -Pnative

# The deployment Image
FROM container-registry.oracle.com/os/oraclelinux:8-slim

EXPOSE 8080

# Copy the native executable into the containers
COPY --from=builder /build/target/gpt-4-copilot app
ENTRYPOINT ["/app"]
