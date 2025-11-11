# Multi-stage Dockerfile for building and running the Spring Boot app
# Build stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /workspace

# Copy project files and build
COPY . .
RUN chmod +x ./gradlew || true
RUN ./gradlew bootJar --no-daemon -x test

# Run stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# copy jar from build stage
COPY --from=build /workspace/build/libs/*.jar app.jar

ENV JAVA_OPTS=""
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

