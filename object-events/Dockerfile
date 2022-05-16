FROM openjdk:17-alpine

COPY target/object-events*.jar app.jar
COPY target/opentelemetry-javaagent*.jar otel.jar

ENTRYPOINT ["java","-javaagent:/otel.jar","-jar","/app.jar"]

