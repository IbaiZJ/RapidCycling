FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

# Copy only the Maven project inputs needed to build the application.
COPY pom.xml ./
COPY src ./src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd --system --create-home --uid 10001 appuser

COPY --from=build /workspace/target/rapidcycling-*.jar /app/app.jar

EXPOSE 8080

USER appuser

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
