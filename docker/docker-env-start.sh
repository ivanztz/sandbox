#!/bin/bash

echo "Configuring docker env"
docker compose -f docker-compose-env.yml -p sandbox up -d

echo "Running postgres containers"
docker compose -f docker-compose-postgres.yml -p sandbox-postgres up -d

echo "Running kafka containers"
docker compose -f docker-compose-kafka.yml -p sandbox-kafka up -d

echo "Running mongodb containers "
docker compose -f docker-compose-mongo.yml -p sandbox-mongo up -d

echo "Running keycloak containers "
docker compose -f docker-compose-keycloak.yml -p sandbox-keycloak up -d

echo "Running observability containers "
docker compose -f docker-compose-observability.yml -p sandbox-observability up -d

echo  "Docker env started"