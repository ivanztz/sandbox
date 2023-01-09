package com.iz.sandbox.service.validation.impl;

import com.iz.sandbox.config.validation.ValidationDefinition;
import com.iz.sandbox.dto.ObjectDataRequest;
import com.iz.sandbox.service.validation.Validator;
import com.iz.sandbox.service.validation.Violation;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SpiValidatorImpl implements Validator {

    private ValidationDefinition definition;

    @Override
    public List<Violation> validateObject(ObjectDataRequest dto) {
        return null;
    }
}
