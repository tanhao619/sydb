package com.cdyoue.oddJobs.exception;

/**
 * Created by liaoyoule on 2017/4/20.
 */
public class InvalidTokenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;
    private final String[] params;

    public InvalidTokenException(String message, String... params) {
        super(message);
        this.message = message;
        this.params = params;
    }

    public InvalidTokenException() {
        this.message = "invalid token";
        this.params = null;
    }


    public ParameterizedErrorVM getErrorVM() {
        return new ParameterizedErrorVM(message, params);
    }
}
