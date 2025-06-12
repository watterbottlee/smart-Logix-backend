package com.mover.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    // For Long-based fields (e.g., ID)
    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        this(resourceName, fieldName, String.valueOf(fieldValue));
    }

    // For String-based fields (e.g., email, username)
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super("Resource " + resourceName + " not found with field " + fieldName + " and value " + fieldValue);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
