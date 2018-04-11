package com.cdyoue.oddJobs.error;

import java.io.Serializable;

public class FieldErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;


    private final String field;

    private final String message;

    public FieldErrorVM(String field, String message) {
        this.field = field;
        this.message = message;
    }


    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
