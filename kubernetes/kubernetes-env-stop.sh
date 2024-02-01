#!/bin/bash
echo "Starting kubernetes env"

echo "Removing postgres"
kubectl delete -f deployment-postgres.yaml

echo "Removing kafka"
kubectl delete -f deployment-kafka.yaml

echo "Removing mongo "
kubectl delete -f deployment-mongo.yaml

echo "Removing keycloak"
kubectl delete -f deployment-keycloak.yaml

echo "Removing logging"
kubectl delete -f deployment-logging.yaml

echo "Removing prometheus"
kubectl delete -f deployment-prometheus.yaml

echo "Removing tracing"
kubectl delete -f deployment-tracing.yaml

echo "Removing grafana"
kubectl delete -f deployment-grafana.yaml
kubectl delete -f deployment-grafana-dashboards.yaml


echo  "Removing ingresses"
kubectl delete -f ingress-env.yaml

echo "Kubernetes env started"
