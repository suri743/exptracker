package com.suri.exptracker.service.validations;

import com.suri.exptracker.entity.constant.EntityName;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface Validations<T> {

    void applyValidation(T curImg, T befImg, ValidationOperation operation);

    private ValidationProperty getValidationProperty() {
        return this.getClass().getAnnotation(ValidationProperty.class);
    }

    default Set<ValidationOperation> operations() {
        return new HashSet<>(Arrays.asList(this.getValidationProperty().operation()));
    }

    default Set<EntityName> entityNames() {
        return new HashSet<>(Arrays.asList(this.getValidationProperty().entityName()));
    }

}
