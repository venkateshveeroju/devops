FROM openjdk:17-jdk-alpine
ADD target/devops-0.0.1-SNAPSHOT.jar devops.jar
ENTRYPOINT java -jar devops.jar com.devops.DevopsApplication