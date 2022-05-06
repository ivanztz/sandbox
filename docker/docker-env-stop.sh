#!/bin/bash

echo "Stopping env containers"
exec docker-compose -f docker-compose-env.yml down