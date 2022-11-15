#!/bin/bash

echo "Stopping kubernetes env"

echo "Removing postgres containers"
kubectl delete -f deployment-postgres.yaml

echo "Removing kafka containers"
kubectl delete -f deployment-kafka.yaml

echo "Removing mongo containers"
kubectl delete -f deployment-mongo.yaml

echo "Removing vector containers"
kubectl delete -k ./kustomize/vector

echo "Removing observability containers"
kubectl delete -f deployment-observability.yaml

echo "Removing observability configs"
kubectl delete -f deployment-observability-config.yaml

echo "Removing mongo containers"
kubectl delete -f deployment-keycloak.yaml

echo "Kubernetes env stopped"
