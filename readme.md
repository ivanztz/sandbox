# Sandbox project

Sandbox project with java microservices to be used for educational or project accelerator purposes  
Simplified data flow is described at [flow diagram](./object-api/src/main/resources/flows/object-modify.puml)


## Modules

- **object-api** - contains services specifications and UML flow diagrams
- **object-service** - simple service for object CRUD operations and filtering
- **object-events** - simple service for storing object events

## Architecture (component view) 
![Deployment](sandbox.png)

## Technologies\frameworks used:

### Specification

- OpenApi 3
- AsyncApi 2.3
- PlantUml

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
- Maven
- Postgresql
- MongoDB
- Kafka
- Keycloak

### Deployment

- Docker
- Kubernetes

### Tracing/Monitoring

- OpenTelemetry + OTEL Collector
- Jaeger
- Prometheus
- Grafana

## Deployment instructions

### Pre-requirements
 - Docker or Minikube
 - Maven
 - JDK 17+

### OS setup 
To make JWT tokens generated from postman correctly work inside container environment it is required to ensure keycloak issue URL remains the same as it compares iss value during validation phase. For this purpose it is required to extend OS hosts configuration with: 
```127.0.0.1 keycloak```

### Docker environment

- Run ```mvn clean install``` to build project artifacts
- Use ```./docker/docker-env-start.sh``` to setup project environment and ```./docker/docker-env-stop.sh``` to clean it.
  In case persistent data needs to be removed use ```docker-env-full-cleanup.sh```
- After environment is initialized use ```./docker/docker-app-start.sh``` to start services
  and ```./docker/docker-app-stop.sh``` to stop them.

#### Endpoints by default available at:

- REST endpoints - http://localhost:8080
- Postgres adminer - http://localhost:8088 (admin:password)
- Kafka UI - http://localhost:8092
- MongoDB Express - http://localhost:8081 (admin:password)
- Jaeger - http://localhost:16686
- Prometheus - http://localhost:9090
- Grafana - _http://localhost:3000_ (admin:password)
- Keycloak http://keycloak:8083 (admin:password)

### Minikube environment

> **Important**. Minikube specifics is only used for pushing application images inside Kubernetes. Environment scripts
> can be used with any other Kubernetes deployment

- Run ```mvn clean install``` to build project artifacts
- Ensure minikube is running. Use ```minikube dashboard``` to access web UI
- Use ```./kubernetes/kubernetes-env-start.sh``` to setup project environment
  and ```./kubernetes/kubernetes-env-stop.sh``` to clean it. In case persistent data needs to be removed
  use ```kubernetes-env-full-cleanup.sh```
- After environment is initialized use ```./kubernetes/kubernetes-app-start.sh``` to start services
  and ```./kubernetes/kubernetes-app-stop.sh``` to stop them.
- Use ```minikube tunnel``` to expose services to local machine