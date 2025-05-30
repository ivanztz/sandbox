package com.iz.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Mongo DB dates converter as it doesn't support OffsetDateTime natively
 */
@Configuration
public class MongoConverterConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(List.of(
                new InstantReadConverter(),
                new InstantWriteConverter()
        ));
    }

    static class InstantWriteConverter implements Converter<Instant, Date> {

        @Override
        public Date convert(Instant source) {
            return Date.from(source);
        }
    }

    static class InstantReadConverter implements Converter<Date, Instant> {
        @Override
        public Instant convert(Date source) {
            return source.toInstant();
        }
    }
}
