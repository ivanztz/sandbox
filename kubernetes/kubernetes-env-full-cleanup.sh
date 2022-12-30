#!/bin/bash

echo "Running full cleanup"

echo "Removing env services"

kubectl delete -f deployment-kafka-svc.yaml
kubectl delete -f deployment-keycloak-svc.yaml
kubectl delete -f deployment-mongo-svc.yaml
kubectl delete -f deployment-observability-svc.yaml
kubectl delete -f deployment-postgres-svc.yaml

echo "Removing app services"

kubectl delete -f deployment-app-svc.yaml

sh ./kubernetes-env-stop.sh

echo "Cleanup complete"
