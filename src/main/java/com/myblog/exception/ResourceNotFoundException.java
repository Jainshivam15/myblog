package com.myblog.exception;
// new ResourceNotFound Exception ("post", "id","1")

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
      private String resourceName;    //Entity class name
      private String fieldName;   //not found is field name
      private long fieldValue;   //id no. is field value

      public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue ) {
          super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue)); // every %$ will take take value of variable
          this.resourceName =resourceName;
          this.fieldName = fieldName;
          this.fieldValue = fieldValue;
      }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
