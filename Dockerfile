# Этап 1: Сборка приложения
FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Этап 2: Запуск приложения
FROM openjdk:latest
COPY --from=build /home/gradle/src/build/libs/demo-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
