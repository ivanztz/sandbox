#!/bin/bash

echo "Starting applications"

# making docker commands to work inside minikube docker
eval $(minikube docker-env)

echo "Building app images"
docker build -t object-service:latest ../object-service
docker build -t object-events:latest ../object-events

echo "Running app containers"
kubectl apply -f deployment-app.yaml

echo "Docker env started"
