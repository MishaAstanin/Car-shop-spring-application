FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk
COPY . .
RUN chmod +x gradlew &&./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/auto_shop-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
