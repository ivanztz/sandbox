#!/bin/bash

echo "Stopping applications"

echo "Removing apps"
kubectl delete -f deployment-app.yaml

echo  "Removing ingresses"
kubectl apply -f ingress-app.yaml

echo "Applications stopped"
