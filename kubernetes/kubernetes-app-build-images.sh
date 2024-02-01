#!/bin/bash

echo "Building applications"

# making docker commands to work inside minikube docker
eval $(minikube docker-env)

echo "Building app images"
mvn -f ../pom.xml clean install -PbuildDocker -Dmaven.test.skip=true

echo "Build complete"
