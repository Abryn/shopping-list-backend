# Step 1: Build application with Maven & OpenJDK 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build JAR file
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Create production image with JRE 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the finished JAR file from the build step
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]