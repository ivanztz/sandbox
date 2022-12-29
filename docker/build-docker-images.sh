#!/bin/bash


echo "Building env containers"

echo "Building schema-registry-provisioning"

(cd ../deploy_common/schema/ && sh ./build-docker-image.sh)

echo "Build finished "