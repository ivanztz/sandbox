FROM curlimages/curl:7.86.0

USER root

ENV SCHEMA_REGISRTY http://schemaregistry:8081

COPY ./schemaregistry_provision.sh /etc/schema/schemaregistry_provision.sh

RUN chmod -R +x /etc/schema/schemaregistry_provision.sh

VOLUME /etc/schema/import

ENTRYPOINT ["/bin/sh","/etc/schema/schemaregistry_provision.sh"]