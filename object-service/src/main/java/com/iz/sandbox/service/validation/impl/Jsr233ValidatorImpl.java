package com.iz.sandbox.service.validation.impl;

import com.iz.sandbox.config.validation.ValidationDefinition;
import com.iz.sandbox.dto.ObjectDataRequest;
import com.iz.sandbox.service.validation.Validator;
import com.iz.sandbox.service.validation.Violation;
import org.springframework.core.io.ClassPathResource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Jsr233ValidatorImpl implements Validator {

    private Validator validatorDelegate;

    public Jsr233ValidatorImpl(ValidationDefinition definition, ScriptEngine scriptEngine) throws IOException, ScriptException {
        final String script = new String(new ClassPathResource(definition.getPath()).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        //adding script to engine
        //not thread safe. Scripts should be loaded sequentally
        scriptEngine.eval(script);
        //getting function to validator interface
        validatorDelegate = ((Invocable) scriptEngine).getInterface(Validator.class);
    }

    @Override
    public List<Violation> validateObject(ObjectDataRequest dto) {
        return validatorDelegate.validateObject(dto);
    }
}
