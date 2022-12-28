#!/bin/sh

echo "Checking schema registry status at $SCHEMA_REGISTRY"

status=undefined
while [ "$status" = "undefined" ]; do
  subjects=$(curl $SCHEMA_REGISTRY/subjects)
  echo "Check result: $subjects"

  if expr "$subjects" : "^\[.*\]$" > /dev/null ; then
    status="provision"
  else
    echo "Waiting for schema registry is up"
    sleep 1
  fi
done

if [ "$status" = "provision" ]; then
  echo "Starting importing schemas from /etc/schema/import"

  mvn -f /etc/schema/import/provision.pom.xml io.confluent:kafka-schema-registry-maven-plugin:register -Dschema.path=/etc/schema/import -Dschema.registry.url=$SCHEMA_REGISRTY -Dmaven.repo.local=/etc/schema/.m2

  echo "Finishing import"
fi

exit 0
