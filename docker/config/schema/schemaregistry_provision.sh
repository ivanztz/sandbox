#!/bin/sh

echo "Checking schema registry status at $SCHEMA_REGISTRY"

status=undefined
while [ "$status" == "undefined" ]; do
  subjects=$(curl $SCHEMA_REGISTRY/subjects)
  echo "Check result: $subjects"

  if [[ "$subjects" =~ ^[\[].*[\]]$ ]]; then
    status="provision"
  else
    echo "Waiting for schema registry is up"
    sleep 1
  fi
done

if [ $status == "provision" ]; then
  echo "Starting importing schemas from /etc/schema/import"
  for schema in $(find /etc/schema/import -type f -name "*.avsc"); do
    echo "Importing schema $schema to $SCHEMA_REGISTRY"
    # extracting subject value from avro file
    subject=$(cat $schema | grep subject | awk -F"\"" '{print $4}')

    if [[ $subjects == *$subject* ]]; then
      echo "Subject $subject is already provisioned. Skipping execution"
    else
      # building schema request string from avro file. Sed replaces new lines and escapes backslashes and double quotes
      data="{\"schema\":\"$(cat $schema | tr -d '\n' | sed 's/\n//g; s/\\/\\\\/g; s/"/\\"/g')\", \"schemaType\": \"AVRO\"}"
      echo "Provisioning schema for subject $subject : $data"

      result=$(curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data "$data" $SCHEMA_REGISTRY/subjects/$subject/versions)
      echo "Result for $subject: $result"
    fi
  done

  echo "Finishing import"
else
  echo "Skipping provisioning"
fi

exit 0
