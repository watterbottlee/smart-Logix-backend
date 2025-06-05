package com.mover.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Long fieldValue;
    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue){
        super("resource "+resourceName+" not found with field"+ fieldName +" and value "+ String.valueOf(fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
