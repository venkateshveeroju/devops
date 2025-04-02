# Stage 1: Build the JAR file using OpenJDK 17 + Maven installation
FROM openjdk:17-jdk-alpine AS build
# Install Maven (since it's not included in the base OpenJDK image)
RUN apk add --no-cache maven

COPY . .
RUN mvn clean install

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-alpine

COPY --from=build /target/devops-0.0.1-SNAPSHOT.jar devops.jar
ENTRYPOINT java -jar devops.jar com.devops.DevopsApplication

