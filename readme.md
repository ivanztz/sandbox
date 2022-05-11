# Sandbox project
Sandbox project with java microservices to be used for educational or project accelerator purposes  
Simplified data flow is described at [flow diagram](./object-api/src/main/resources/flows/object-modify.puml)

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
  - Lombok
  - MapStruct
  - Liquibase
- Maven
- Postgresql
- MongoDB
- Kafka

### Deployment

- Docker

## Running instructions

### Docker environment
Use ```./docker/docker-env-start.sh``` to setup project environment and ```./docker/docker-env-use.sh``` to clean it 

#### Administration consoles by default available at:
- Postgres adminer- http://localhost:8088 
- Kafka UI - http://localhost:8092
- MongoDB Express - http://localhost:8081