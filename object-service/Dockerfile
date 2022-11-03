FROM eclipse-temurin:17-jre

COPY target/object-service*.jar app.jar
COPY target/otel/opentelemetry-javaagent*.jar otel.jar

ENTRYPOINT ["java","-javaagent:/otel.jar","-jar","/app.jar"]

