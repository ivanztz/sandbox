package com.iz.sandbox.config.validation;

import com.iz.sandbox.service.validation.Validator;
import com.iz.sandbox.service.validation.impl.Jsr233ValidatorImpl;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Configuration("validationConfiguration")
public class ValidationConfiguration {
    private final ScriptEngine kotlinScriptEngine = new ScriptEngineManager().getEngineByExtension("kts");
    private final ScriptEngine groovyScriptEngine = new ScriptEngineManager().getEngineByExtension("groovy");
    @Getter
    private final List<Validator> validators;

    public ValidationConfiguration(ValidationDefinitionConfiguration configuration, List<Validator> validatorBeans) {
        validators = initValidators(configuration, validatorBeans);
    }

    @NotNull
    private List<Validator> initValidators(ValidationDefinitionConfiguration configuration, List<Validator> validatorBeans) {

        final Map<String, Validator> validatorBeansMap = Optional.ofNullable(validatorBeans).orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toUnmodifiableMap(validator -> validator.getClass().getName(), validator -> validator));

        return Optional.ofNullable(configuration.getDefinitions()).orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparingInt(ValidationDefinition::getOrder))
                .map(def -> initializeValidator(def, validatorBeansMap))
                .collect(Collectors.toList());
    }

    private Validator initializeValidator(ValidationDefinition definition, Map<String, Validator> validatorBeansMap) {
        try {
            return switch (definition.getType()) {
                case BEAN -> Optional.ofNullable(validatorBeansMap.
                        get(definition.getClassName())).orElseThrow(() -> new NoSuchElementException("Validator not found: " + definition.getClassName()));
                case GROOVY -> new Jsr233ValidatorImpl(definition, groovyScriptEngine);
                case KOTLIN -> new Jsr233ValidatorImpl(definition, kotlinScriptEngine);
                default -> throw new IllegalArgumentException("Unknown validator type " + definition.getType());
            };
        } catch (IOException | ScriptException e) {
            throw new RuntimeException(e);
        }
    }

}
