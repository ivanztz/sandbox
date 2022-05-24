#!/bin/bash

echo  "Removing kubernetes env"

echo "Removing postgres containers"
kubectl delete -f deployment-postgres.yaml

echo "Removing kafka containers"
kubectl delete -f deployment-kafka.yaml

echo "Removing mongo containers"
kubectl delete -f deployment-mongo.yaml

echo "Removing tracing containers"
kubectl delete -f deployment-tracing.yaml

echo "Removing postgres volumes"
kubectl delete -f deployment-postgres-pvc.yaml

echo "Removing kafka volumes"
kubectl delete -f deployment-kafka-pvc.yaml

echo "Removing mongo volumes"
kubectl delete -f deployment-mongo-pvc.yaml

echo  "Kubernetes env removed"