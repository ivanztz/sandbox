#!/bin/bash

echo "Stopping applications"

echo "Removing app containers"
docker compose -f docker-compose-app.yml -p sandbox-app down

echo "Applications stopped"
