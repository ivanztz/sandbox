#!/bin/bash

echo "Removing postgres containers"
docker compose -f docker-compose-postgres.yml -p sandbox-postgres down

echo "Removing kafka containers"
docker compose -f docker-compose-kafka.yml -p sandbox-kafka down

echo "Removing mongodb containers "
docker compose -f docker-compose-mongo.yml -p sandbox-mongo down

echo "Removing keycloak containers "
docker compose -f docker-compose-keycloak.yml -p sandbox-keycloak down

echo "Removing tracing containers "
docker compose -f docker-compose-tracing.yml -p sandbox-tracing down

echo "Removing monitoring containers "
docker compose -f docker-compose-monitoring.yml -p sandbox-monitoring down

echo "Removing docker env"
docker compose -f docker-compose-env.yml -p sandbox down

echo "Docker env stopped"
