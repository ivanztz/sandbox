#!/bin/bash
echo "Starting kubernetes env"

echo "Deploying postgres"
kubectl apply -f deployment-postgres.yaml

echo "Deploying kafka"
kubectl apply -f deployment-kafka.yaml

echo "Deploying mongo "
kubectl apply -f deployment-mongo.yaml

echo "Deploying keycloak"
kubectl apply -f deployment-keycloak.yaml

echo "Deploying logging"
kubectl apply -f deployment-logging.yaml

echo "Deploying prometheus"
kubectl apply -f deployment-prometheus.yaml

echo "Deploying tracing"
kubectl apply -f deployment-tracing.yaml

echo "Deploying profiling"
kubectl apply -f deployment-profiling.yaml

echo "Deploying grafana"
kubectl apply -f deployment-grafana-dashboards.yaml
kubectl apply -f deployment-grafana.yaml


echo  "Configuring ingresses"
kubectl apply -f ingress-env.yaml

echo "Kubernetes env started"
