## ---------- build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom and download dependencies (better build cache)
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Copy source and build the Spring Boot fat jar
COPY src ./src
RUN mvn -q -DskipTests package


## ---------- runtime stage ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "app.jar"]