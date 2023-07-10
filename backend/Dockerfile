FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app
COPY backend .
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY --from=build /app/target/dishgenius-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "dishgenius-0.0.1-SNAPSHOT.jar"]
