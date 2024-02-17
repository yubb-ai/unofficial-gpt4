# Using Oracle GraalVM for JDK 17
FROM devatherock/graalvm:21-ol8 AS builder

# Set the working directory to /build
WORKDIR /build

# Copy the source code into the image for building
COPY . /build

# Add execute permission to mvnw script and build
RUN chmod +x mvnw && \
    ./mvnw --no-transfer-progress package -Pnative && \
    rm -rf /root/.m2

# Use Scratch as base image
FROM scratch

# Copy the native executable into the containers
COPY --from=builder /build/target/gpt-4-copilot /gpt-4-copilot

# Copy the tzdata from builder image
COPY --from=builder /usr/share/zoneinfo /usr/share/zoneinfo

# Set the entrypoint
ENTRYPOINT ["/gpt-4-copilot"]

