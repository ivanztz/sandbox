# Sandbox project
Sandbox project with java microservices to be used for educational or project accelerator purposes  
Simplified data flow is described at [flow diagram](./object-api/src/main/resources/flows/object-modify.puml)

## Modules
- **object-api** - contains services specifications and UML flow diagrams 
- **object-service** - simple service for object CRUD operations and filtering
- **object-events** - simple service for storing object events

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
  - Lombok
  - MapStruct
  - Liquibase
  - Micrometer
- Maven
- Postgresql
- MongoDB
- Kafka

### Deployment

- Docker

### Tracing/Monitoring
- OpenTelemetry
- Jaeger
- Prometheus
- Grafana

## Running instructions

### Docker environment
- Run ```mvn clean install``` to build project artifacts
- Use ```./docker/docker-env-start.sh``` to setup project environment and ```./docker/docker-env-stop.sh``` to clean it 
- After environment is initialized use ```./docker/docker-app-start.sh``` to start services and ```./docker/docker-app-stop.sh``` to stop them

#### Endpoints by default available at:
- REST endpoints - http://localhost:8080
- Postgres adminer - http://localhost:8088 (admin:password)
- Kafka UI - http://localhost:8092
- MongoDB Express - http://localhost:8081 (admin:password)
- Jaeger - http://localhost:16686 
- Prometheus - http://localhost:9090 
- Grafana - http://localhost:3000 (admin:password)