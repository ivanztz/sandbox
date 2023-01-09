package com.iz.sandbox.service.validation;

import com.iz.sandbox.dto.ObjectDataRequest;

import java.util.List;

public interface ValidationService {
    List<Violation> validateObject(ObjectDataRequest dto);
}
