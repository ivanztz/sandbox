package com.iz.sandbox.service.validation;

import com.iz.sandbox.object.dto.ObjectDataRequest;

import java.util.List;

public interface Validator {
    List<Violation> validateObject(ObjectDataRequest dto);
}
