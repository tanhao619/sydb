package com.cdyoue.oddJobs.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View Model for transferring error message with a list of field errors.
 */
public class ErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;

    private List<FieldErrorVM> fieldErrors;


    public ErrorVM(String message) {
        this.message = message;
    }

    public ErrorVM(String message, List<FieldErrorVM> fieldErrors) {
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public void add(String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorVM( field, message));
    }

    public String getMessage() {
        return message;
    }


    public List<FieldErrorVM> getFieldErrors() {
        return fieldErrors;
    }
}
