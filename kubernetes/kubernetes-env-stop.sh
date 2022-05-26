#!/bin/bash

echo "Stopping kubernetes env"

echo "Removing postgres containers"
kubectl delete -f deployment-postgres.yaml

echo "Removing kafka containers"
kubectl delete -f deployment-kafka.yaml

echo "Removing mongo containers"
kubectl delete -f deployment-mongo.yaml

echo "Removing tracing containers"
kubectl delete -f deployment-tracing.yaml

echo "Removing prometheus containers"
kubectl delete -f deployment-prometheus.yaml

echo "Removing grafana containers"
kubectl delete -f deployment-grafana.yaml

echo "Kubernetes env stopped"
