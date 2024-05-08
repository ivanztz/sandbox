# Sandbox project

Sandbox project with java microservices to be used for educational or project accelerator purposes


## Modules

- **object-api** - contains services specifications and UML flow diagrams
- **object-service** - simple service for object CRUD operations and filtering
- **object-events** - simple service for storing object events

## DocHub
DocHub description is available via IntelliJ or VSCode Plugins and contains detailed information about project structure 

### Deployment diagram
![Deployment](docs/docs/sandbox.png)

## Technologies\frameworks used:

### Specification

- OpenApi 3
- JSON Schema
- Avro

### Development

- Java
- Frameworks
    - Spring
    - Spring Boot
    - Spring Data
    - Spring Kafka
    - Spring Metrics
    - Spring security (Resource server)
    - Lombok
    - MapStruct
    - Liquibase
    - Micrometer
    - Springdoc
- Maven
- Postgresql
- MongoDB
- Kafka + Schema registry
- Keycloak

### Deployment

- Kubernetes

### Tracing/Monitoring/Logging

- OpenTelemetry + OTEL Collector + Custom agent extension for sampling
- Prometheus
- Grafana + Tempo + Loki + Pyroscope
- Promtail

## Deployment instructions

### Pre-requirements
 - Minikube or other Kubernetes cluster
 - Maven
 - JDK 17+

### Minikube environment
> **Important**. Minikube specifics in bash scripts is only used for pushing application images inside Kubernetes. Environment scripts
> can be used with any other Kubernetes deployment

#### Pre-requisites 
Ensure minikube ingress addon is enabled by running:
```minikube addons enable ingress```

#### Setup

- Run ```./kubernetes/kubernetes-app-build-images.sh``` to build project artifacts
- Ensure minikube is running. Use ```minikube dashboard``` to access web UI
- Use ```./kubernetes/kubernetes-env-start.sh``` to setup project environment
  and ```./kubernetes/kubernetes-env-stop.sh``` to clean it. Services are kept for redeployment purposes to avoid reconfiguring postman every time. Use ```kubernetes-env-full-cleanup.sh``` for full cleanup.
- After environment is initialized use ```./kubernetes/kubernetes-app-start.sh``` to start services
  and ```./kubernetes/kubernetes-app-stop.sh``` to stop them.
- To access services and 3rd-party tools edit **/etc/hosts** with following value: 
> {{minikube_ip}}	grafana.cluster.local keycloak.cluster.local adminer.cluster.local prometheus.cluster.local kafka-ui.cluster.local mongo-express.cluster.local object-service.cluster.local object-events.cluster.local

## Usage

Postman collection for services REST APIs [Sandbox API.postman_collection.json](Sandbox%20API.postman_collection.json)

### Endpoints by default available at:

- REST endpoints - http://object-service.cluster.local and http://object-events.cluster.local
- Postgres adminer - http://adminer.cluster.local (admin:password)
- Kafka UI - http://kafka-ui.cluster.local
- MongoDB Express - http://mongo-express.cluster.local (admin:password)
- Prometheus Graph - http://prometheus.cluster.local
- Grafana - http://grafana.cluster.local (admin:password)
- Keycloak http://keycloak.cluster.local (admin:password)

