#!/bin/bash

echo "Starting kubernetes env"

echo "Configuring postgres volumes"
kubectl apply -f deployment-postgres-pvc.yaml

echo "Deploying postgres containers"
kubectl apply -f deployment-postgres.yaml

echo "Configuring kafka volumes"
kubectl apply -f deployment-kafka-pvc.yaml

echo "Deploying kafka containers"
kubectl apply -f deployment-kafka.yaml

echo "Configuring mongo volumes"
kubectl apply -f deployment-mongo-pvc.yaml

echo "Deploying mongo containers"
kubectl apply -f deployment-mongo.yaml

echo "Deploying keycloak volumes"
kubectl apply -f deployment-keycloak-pvc.yaml

echo "Deploying keycloak containers"
kubectl apply -f deployment-keycloak.yaml

echo "Deploying vector containers"
kubectl apply -k ./kustomize/vector

echo "Configuring observability volumes"
kubectl apply -f deployment-observability-pvc.yaml

echo "Configuring observability configs"
kubectl apply -f deployment-observability-config.yaml

echo "Deploying observability containers"
kubectl apply -f deployment-observability.yaml


echo "Kubernetes env started"
