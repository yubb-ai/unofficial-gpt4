# Using Oracle GraalVM for JDK 17
FROM container-registry.oracle.com/graalvm/native-image:21-ol8 AS builder

# Set the working directory to /home/app
WORKDIR /build

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY . .

# Build
RUN mvn --no-transfer-progress native:compile -Pnative

# The deployment Image
FROM container-registry.oracle.com/os/oraclelinux:8-slim

EXPOSE 8080

# Copy the native executable into the containers
COPY --from=builder /build/target/gpt-4-copilot app
ENTRYPOINT ["/app"]

