package com.cdyoue.oddJobs.exception;

public class ParamterErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;
    private final String[] params;

    public ParamterErrorException(String message, String... params) {
        super(message);
        this.message = message;
        this.params = params;
    }

    public ParameterizedErrorVM getErrorVM() {
        return new ParameterizedErrorVM(message, params);
    }
}
