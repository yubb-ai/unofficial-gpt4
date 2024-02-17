# Using Oracle GraalVM for JDK 17
FROM devatherock/graalvm:21-ol8 AS builder

# Set the working directory to /build
WORKDIR /build

# Copy the source code into the image for building
COPY . /build

# Add execute permission to mvnw script and build
RUN chmod +x mvnw && \
    ./mvnw --no-transfer-progress native:compile -Pnative && \
    rm -rf /root/.m2

# Use Distroless as base image
FROM container-registry.oracle.com/os/oraclelinux:8-slim

RUN apk update \
    && apk upgrade \
    && apk add --no-cache tzdata

# Copy the native executable into the containers
COPY --from=builder /build/target/gpt-4-copilot /app

# Set the entrypoint
ENTRYPOINT ["/app"]


