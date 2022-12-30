#!/bin/bash

echo "Starting applications"

# making docker commands to work inside minikube docker
eval $(minikube docker-env)

echo "Building app images"
mvn -f ../pom.xml clean install -PbuildDocker -Dmaven.test.skip=true

echo "Running app containers"
kubectl apply -f deployment-app.yaml

echo "Running app services"
kubectl apply -f deployment-app-svc.yaml

echo "Docker env started"
