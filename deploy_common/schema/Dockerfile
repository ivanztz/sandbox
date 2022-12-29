FROM maven:latest

USER root

COPY schemaregistry_provision.sh /etc/schema/schemaregistry_provision.sh
RUN chmod -R +x /etc/schema/schemaregistry_provision.sh

COPY import/ /etc/schema/import/

# building m2 cache for offline work
RUN mvn -f /etc/schema/import/provision.pom.xml dependency:go-offline -Dmaven.repo.local=/etc/schema/.m2


ENTRYPOINT ["/bin/sh","/etc/schema/schemaregistry_provision.sh"]