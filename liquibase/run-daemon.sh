#!/bin/bash

docker run -d -t -i \
--link telegram-liquibase:telegram-postgres \
-e DB_HOST=telegram-postgres \
-e DB_PORT=5432 \
-e DB_NAME=telegram \
-e DB_USERNAME=postgres \
-e DB_PASSWORD=changeme \
-e DB_SCHEMA=telegram \
--name telegram-liquibase telegram-liquibase:1.0
