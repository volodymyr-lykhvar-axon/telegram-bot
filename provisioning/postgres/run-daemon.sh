#!/bin/bash

docker run \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=changeme \
    -e POSTGRES_DB=telegram \
    -e POSTGRES_SCHEMA=telegram \
    -p 5432:5432 \
    -v /home/postgres-data:/var/lib/postgresql/data \
    -d -t -i --name telegram-postgres telegram-postgres