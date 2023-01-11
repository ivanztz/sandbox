package com.iz.sandbox.service.validation.impl;

import com.iz.sandbox.dto.ObjectDataRequest;
import com.iz.sandbox.service.validation.Validator;
import com.iz.sandbox.service.validation.Violation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class DecimalValueValidator implements Validator {

    @Override
    public List<Violation> validateObject(ObjectDataRequest dto) {
        return (dto.getDecimalField() > 50d) ?
                List.of(new Violation("decimal_size_check", "Decimal size must be lover than 50"))
                : Collections.emptyList();
    }
}
