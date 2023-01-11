package com.iz.sandbox.service.validation.exception;

import com.iz.sandbox.service.validation.Violation;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final List<Violation> violations;

    public ValidationException(List<Violation> violations) {
        this.violations = violations;
    }

}
