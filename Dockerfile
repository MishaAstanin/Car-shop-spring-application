FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk curl || true
# Установка Gradle Wrapper, если он еще не установлен
RUN curl -L https://services.gradle.org/distributions/gradle-7.3-bin.zip -o /tmp/gradle-7.3-bin.zip && unzip /tmp/gradle-7.3-bin.zip -d /opt/gradle && rm /tmp/gradle-7.3-bin.zip
ENV PATH="/opt/gradle/bin:${PATH}"
COPY..
RUN chmod +x gradlew &&./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/auto_shop-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
