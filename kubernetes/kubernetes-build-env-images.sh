#!/bin/bash


echo "Building env containers"

# making docker commands to work inside minikube docker
eval $(minikube docker-env)

echo "Building schema-registry-provisioning"

(cd ../deploy_common/schema/ && sh ./build-docker-image.sh)

echo "Build finished "