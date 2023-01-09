package com.iz.sandbox.config.validation;

import com.iz.sandbox.service.validation.Validator;
import com.iz.sandbox.service.validation.impl.Jsr233ValidatorImpl;
import com.iz.sandbox.service.validation.impl.SpiValidatorImpl;
import lombok.Getter;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngineFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@Getter
public class ValidationConfiguration {

    private final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private final ScriptEngine kotlinScriptEngine = new KotlinJsr223JvmLocalScriptEngineFactory().getScriptEngine();

    private final ScriptEngine groovyScriptEngine = new GroovyScriptEngineFactory().getScriptEngine();

    @Bean
    public List<Validator> validators(ValidationDefinitionConfiguration configuration) {
        return Optional.ofNullable(configuration.getDefinitions()).orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparingInt(ValidationDefinition::getOrder))
                .map(this::initializeValidator)
                .collect(Collectors.toList());

    }

    private Validator initializeValidator(ValidationDefinition definition) {
        try {
            switch (definition.getType()) {
                case SPI:
                    return new SpiValidatorImpl(definition);
                case GROOVY:
                    return new Jsr233ValidatorImpl(definition, groovyScriptEngine);
                case KOTLIN:
                    return new Jsr233ValidatorImpl(definition, kotlinScriptEngine);
                default:
                    throw new IllegalArgumentException("Unknown validator type " + definition.getType());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

}
