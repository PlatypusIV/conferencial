FROM eclipse-temurin:22-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean install package

FROM openjdk:22
WORKDIR /app
COPY --from=build /app/target/*.jar conferencial.jar
EXPOSE 8080

ENTRYPOINT [ "java", "-jar","conferencial.jar" ]