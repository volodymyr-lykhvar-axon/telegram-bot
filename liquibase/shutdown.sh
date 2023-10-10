#!/bin/bash

docker rm -v telegram-liquibase || true
docker rmi telegram-liquibase:1.0 || true
