package com.iz.sandbox.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
@AllArgsConstructor
public class KafkaConfig {

    private KafkaProperties kafkaProperties;
    private ObjectMapper objectMapper;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> producerProperties = kafkaProperties.buildProducerProperties();
        // making spring object mapper work with kafka json serializer
        JsonSerializer<Object> valueSerializer = new JsonSerializer<>(objectMapper);
        valueSerializer.configure(producerProperties, false);

        return new DefaultKafkaProducerFactory<>(producerProperties, new StringSerializer(), valueSerializer);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
