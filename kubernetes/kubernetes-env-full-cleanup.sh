#!/bin/bash

echo "Removing kubernetes env"

echo "Removing postgres containers"
kubectl delete -f deployment-postgres.yaml

echo "Removing kafka containers"
kubectl delete -f deployment-kafka.yaml

echo "Removing mongo containers"
kubectl delete -f deployment-mongo.yaml

echo "Removing observability containers"
kubectl delete -f deployment-observability.yaml

echo "Removing vector containers"
kubectl delete -k ./kustomize/vector

echo  "Removing pods"
kubectl delete --all pods --namespace=default --force

echo "Removing postgres volumes"
kubectl delete -f deployment-postgres-pvc.yaml

echo "Removing kafka volumes"
kubectl delete -f deployment-kafka-pvc.yaml

echo "Removing mongo volumes"
kubectl delete -f deployment-mongo-pvc.yaml

echo "Removing observability volumes"
kubectl delete -f deployment-observability-pvc.yaml

echo "Removing observability configs"
kubectl delete -f deployment-observability-config.yaml

echo "Removing keycloak containers"
kubectl delete -f deployment-keycloak.yaml

echo "Removing keycloak volumes"
kubectl delete -f deployment-keycloak-postgres-pvc.yaml

echo "Kubernetes env removed"
