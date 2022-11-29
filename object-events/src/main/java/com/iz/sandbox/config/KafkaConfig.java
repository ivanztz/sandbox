package com.iz.sandbox.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.util.Map;

@Configuration
@AllArgsConstructor
public class KafkaConfig {
    private KafkaProperties kafkaProperties;

    private ObjectMapper objectMapper;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties();

        return new DefaultKafkaConsumerFactory<>(consumerProperties,
                new ErrorHandlingDeserializer<>(new StringDeserializer()),
                new ErrorHandlingDeserializer<>(new KafkaAvroDeserializer()));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        final DefaultErrorHandler errorHandler = new DefaultErrorHandler();
        errorHandler.setAckAfterHandle(false);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }


}
