#!/bin/bash

echo "Starting applications"

echo "Running apps"
kubectl apply -f deployment-app.yaml

echo  "Configuring ingresses"
kubectl apply -f ingress-app.yaml

echo "Docker env started"
