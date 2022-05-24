#!/bin/bash

echo "Stopping applications"

echo "Removing app containers"
kubectl delete -f deployment-app.yaml

echo "Applications stopped"
