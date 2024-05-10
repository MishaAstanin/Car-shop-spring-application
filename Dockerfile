# Используйте официальный образ OpenJDK 17
FROM openjdk:17-jdk-alpine

# Установите переменные окружения
ENV SPRING_APPLICATION_NAME=auto_shop \
    SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/carshop \
    SPRING_DATASOURCE_USERNAME=postgres \
    SPRING_DATASOURCE_PASSWORD=100904misha \
    SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect \
    SPRING_JPA_HIBERNATE_DDL_AUTO=update \
    SPRING_JPA_SHOW_SQL=true \
    SPRING_JPA_OPEN_IN_VIEW=false \
    SPRING_MVC_STATIC_PATH_PATTERN=/static/** \
    SPRING_MAIL_HOST=smtp.yandex.ru \
    SPRING_MAIL_PORT=465 \
    SPRING_MAIL_PROTOCOL=smtps \
    SPRING_MAIL_USERNAME=bokgosha@yandex.ru \
    SPRING_MAIL_PASSWORD=qtxselgtcdgeljsm \
    SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true \
    SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true \
    SERVER_PORT=8080


# Копируйте JAR файл в контейнер
COPY build/libs/auto_shop-0.0.1-SNAPSHOT.jar /app/auto-shop.jar
# Запустите приложение
ENTRYPOINT ["java", "-jar", "/app/auto-shop.jar"]