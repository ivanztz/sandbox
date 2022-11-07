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
kubectl apply -f deployment-keycloak-postgres-pvc.yaml

echo "Deploying keycloak containers"
kubectl apply -f deployment-keycloak.yaml

echo "Deploying tracing containers"
kubectl apply -f deployment-tracing.yaml

echo "Configuring prometheus volumes"
kubectl apply -f deployment-prometheus-pvc.yaml

echo "Configuring grafana volumes"
kubectl apply -f deployment-grafana-pvc.yaml

echo "Deploying prometheus containers"
kubectl apply -f deployment-prometheus.yaml

echo "Deploying grafana containers"
kubectl apply -f deployment-grafana.yaml

echo "Kubernetes env started"
