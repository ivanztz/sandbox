#!/bin/bash

echo "Running env containers"
exec docker-compose -f docker-compose-env.yml up -d