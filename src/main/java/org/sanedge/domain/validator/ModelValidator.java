package org.sanedge.domain.validator;

import java.util.Set;
import java.util.stream.Collectors;

import org.sanedge.domain.exception.ModelValidationException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ModelValidator {

    private final Validator validator;

    public <T> T validate(T model) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(model);

        if (!constraintViolations.isEmpty()) {
            final var messages = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new ModelValidationException(messages);
        }

        return model;
    }
}