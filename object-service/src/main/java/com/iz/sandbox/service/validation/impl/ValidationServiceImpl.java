package com.iz.sandbox.service.validation.impl;

import com.iz.sandbox.config.validation.ValidationConfiguration;
import com.iz.sandbox.object.dto.ObjectDataRequest;
import com.iz.sandbox.service.validation.ValidationService;
import com.iz.sandbox.service.validation.Violation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class ValidationServiceImpl implements ValidationService {

    private ValidationConfiguration configuration;

    @Override
    public List<Violation> validateObject(ObjectDataRequest dto) {
        return configuration.getValidators()
                .stream()
                .map(val -> val.validateObject(dto))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
