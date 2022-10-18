#!/bin/bash

echo "Removing postgres containers"
docker compose -f docker-compose-postgres.yml -p sandbox-postgres down

echo "Removing kafka containers"
docker compose -f docker-compose-kafka.yml -p sandbox-kafka down

echo "Removing mongodb containers "
docker compose -f docker-compose-mongo.yml -p sandbox-mongo down

echo "Removing tracing containers "
docker compose -f docker-compose-tracing.yml -p sandbox-tracing down

echo "Removing monitoring containers "
docker compose -f docker-compose-monitoring.yml -p sandbox-monitoring down

echo "Removing docker env"
docker compose -f docker-compose-env.yml -p sandbox down

echo "Removing postgres volumes"
docker volume rm sandbox-postgres_db-data

echo "Removing mongo volumes"
docker volume rm sandbox-mongo_mongo-data

echo "Removing kafka volumes"
docker volume rm sandbox-kafka_zookeeper-data
docker volume rm sandbox-kafka_kafka-data

echo "Removing monitoring volumes"
docker volume rm sandbox-monitoring_grafana-data
docker volume rm sandbox-monitoring_prometheus-data

echo "Removing keycloak volumes"
docker volume rm sandbox-keycloak_keycloak-db-data

echo "Docker env stopped"
