FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

# Variabili di default (override via env) sono la stessa fallback che passo in application.properties, se volete ignoratele
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/student_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=toor
ENV APP_PROFILE=docker

ENTRYPOINT ["java", "-jar", "app.jar"]