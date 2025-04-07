#!/bin/bash

./mvnw clean package -DskipTests

docker compose -f docker-compose.yml --env-file .envs/.env up --build --force-recreate -d
