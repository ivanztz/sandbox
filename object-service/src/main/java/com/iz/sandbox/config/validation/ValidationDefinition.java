package com.iz.sandbox.config.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ValidationDefinition {
    private ValidationScriptType type;
    private String name;
    private String className;
    private String path;
    private int order;

    public enum ValidationScriptType {
        KOTLIN,
        GROOVY,
        BEAN
    }
}