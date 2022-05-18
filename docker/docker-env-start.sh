#!/bin/bash

echo "Configuring docker env"
docker-compose -f docker-compose-env.yml -p sandbox up -d

echo "Running postgres containers"
docker-compose -f docker-compose-postgres.yml -p sandbox-postgres up -d

echo "Running kafka containers"
docker-compose -f docker-compose-kafka.yml -p sandbox-kafka up -d

echo "Running mongodb containers "
docker-compose -f docker-compose-mongo.yml -p sandbox-mongo up -d

echo "Running tracing containers "
docker-compose -f docker-compose-tracing.yml -p sandbox-tracing up -d

echo "Running monitoring containers "
docker-compose -f docker-compose-monitoring.yml -p sandbox-monitoring up -d

echo  "Docker env started"