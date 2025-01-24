package com.suri.exptracker.service.validations;

import com.suri.exptracker.entity.constant.EntityName;
import com.suri.exptracker.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationManager<T> {

    private final List<Validations<T>> validations;

    public void apply(T curImg, T befImg, ValidationOperation operation, EntityName entityName) {
        final List<Validations<T>> applicableValidations =
            getApplicableValidations(entityName, operation);
        for (Validations<T> applicableValidation : applicableValidations) {
            try{
                applicableValidation.applyValidation(curImg, befImg, operation);
            } catch (InvalidRequestException ex) {
                System.err.println("Caught InvalidRequestException: " + ex.getMessage());
                throw ex; // Ensure it is re-thrown
            } catch (Exception ex) {
                System.err.println("Caught Exception: " + ex.getMessage());
                throw ex;
            }
        }
    }

    private List<Validations<T>> getApplicableValidations(
        EntityName entityName, ValidationOperation operation) {
        return this.validations.stream()
            .filter(
                validation ->
                    matchEntity(entityName, validation.entityNames())
                    && matchOperation(operation, validation.operations()))
            .toList();
    }

    private boolean matchEntity(EntityName entityName, Set<EntityName> entityNames) {
        return entityNames.contains(entityName);
    }

    private boolean matchOperation(
        ValidationOperation operation, Set<ValidationOperation> operations) {
        return operations.contains(operation);
    }
}

