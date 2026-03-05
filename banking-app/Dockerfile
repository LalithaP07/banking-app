# ================================
# STAGE 1: Build
# ================================
FROM maven:3.9.5-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# ================================
# STAGE 2: Run
# ================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/banking-app-0.0.1-SNAPSHOT.jar banking-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "banking-app.jar"]
