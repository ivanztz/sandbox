#!/bin/bash

echo "Starting applications"

echo "Building app images"
docker compose -f docker-compose-app.yml -p sandbox-app build

echo "Running app containers"
docker compose -f docker-compose-app.yml -p sandbox-app up -d

echo  "Docker env started"