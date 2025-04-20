FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Копируем только pom.xml, чтобы кешировать зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходники и собираем jar (тесты пропускаем)
COPY src ./src
RUN mvn clean package -DskipTests -B

# ================================
# 2) Запуск собранного JAR
# ================================
FROM openjdk:21-jdk-slim AS runner

WORKDIR /app

# Копируем из builder-а готовый jar
COPY --from=builder /app/target/*.jar app.jar

# Порт, по которому слушает Spring Boot
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]