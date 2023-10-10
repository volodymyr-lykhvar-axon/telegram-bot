# https://hub.docker.com/_/openjdk/
# MAVEN Build Stage
FROM maven:3.8.3-openjdk-17-slim as maven
MAINTAINER <vlykhvar.axon>

# Copy project source
COPY . .
WORKDIR /app

# Building Project Artifact with skipping tests
RUN mvn clean install -DskipTests

# JAVA Build Stage
FROM openjdk:17.0.2-jdk-slim
MAINTAINER <vlykhvar.axon>

# Copy Aritfact from previous stage
RUN mkdir /app
WORKDIR /app

COPY --from=maven /app/api/target/telegram.jar /app/telegram.jar

# Entrypoint directive
ENTRYPOINT java -jar "-Dspring.datasource.url=jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME?currentSchema=$DB_SCHEMA" \
                     "-Dspring.datasource.username=$DB_USERNAME" \
                     "-Dspring.datasource.password=$DB_PASSWORD" \
                     "-Dspring.jpa.properties.hibernate.default_schema=$DB_SCHEMA" \
                     "-Dspring.profiles.active=$PROFILE" \
                     "-Dtelegram.settings.max-users=$MAX_USERS" \
                     "-Dtelegram.settings.price-scheduler-delay=$PRICE_SCHEDULER_DELAY" \
                     "-Dtelegram.settings.notification-percent=$NOTIFICATION_PERCENT" \
                     "-Dtelegram.settings.token=$TOKEN" \
                     /app/telegram.jar
