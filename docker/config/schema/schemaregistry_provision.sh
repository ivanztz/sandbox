#!/bin/sh

echo "Checking schema registry status at $SCHEMA_REGISTRY"

status=undefined
while [  "$status" == "undefined" ];
do
  response=$(curl $SCHEMA_REGISTRY/subjects)
  echo "Check result: $response"

  if [ "$response" == "[]" ]
  then
    status="provision_required"
  else
    if [[ "$response" =~ \[.+\] ]]
    then
      status="provision_not_required"
    else
      sleep 1
    fi
  fi
done

if [ $status == "provision_required" ]
then
  echo "Starting importing schemas from /etc/schema/import"
  for schema in $(ls /etc/schema/import *.avro)
  do
    echo "Importing schema $schema to $SCHEMA_REGISTRY"
    # extracting subject value from avro file
    subject=$(cat /etc/schema/import/$schema | grep subject | awk -F"\"" '{print $4}')

    # building schema request string from avro file. Sed replaces new lines and escapes backslashes and double quotes
    data="{\"schema\":\"$(cat /etc/schema/import/$schema | tr -d '\n' | sed 's/\n//g; s/\\/\\\\/g; s/"/\\"/g')\", \"schemaType\": \"AVRO\"}"
    echo "Creating schema for subject $subject : $data"

    result=$(curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data "$data"  $SCHEMA_REGISTRY/subjects/$subject/versions)
    echo "Result for $subject: $result"
  done

  echo "Finishing import"
else
  echo "Skipping provisioning"
fi

exit 0