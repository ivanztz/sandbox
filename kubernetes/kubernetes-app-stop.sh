#!/bin/bash

echo "Stopping applications"

echo "Removing apps"
kubectl delete -f deployment-app.yaml --force

echo  "Removing ingresses"
kubectl apply -f ingress-app.yaml --force

echo "Applications stopped"
