#!/bin/bash
echo "Starting kubernetes env"

echo "Removing postgres"
kubectl delete -f deployment-postgres.yaml --force

echo "Removing kafka"
kubectl delete -f deployment-kafka.yaml --force

echo "Removing mongo "
kubectl delete -f deployment-mongo.yaml --force

echo "Removing keycloak"
kubectl delete -f deployment-keycloak.yaml --force

echo "Removing logging"
kubectl delete -f deployment-logging.yaml --force

echo "Removing prometheus"
kubectl delete -f deployment-prometheus.yaml --force

echo "Removing tracing"
kubectl delete -f deployment-tracing.yaml --force

echo "Removing profiling"
kubectl delete -f deployment-profiling.yaml --force

echo "Removing grafana"
kubectl delete -f deployment-grafana.yaml --force
kubectl delete -f deployment-grafana-dashboards.yaml --force


echo  "Removing ingresses"
kubectl delete -f ingress-env.yaml --force

echo "Kubernetes env started"
