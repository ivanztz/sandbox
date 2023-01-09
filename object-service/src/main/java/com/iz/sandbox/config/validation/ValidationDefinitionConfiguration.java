package com.iz.sandbox.config.validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "validation")
@Getter
@Setter
public class ValidationDefinitionConfiguration {

    private List<ValidationDefinition> definitions;
}
