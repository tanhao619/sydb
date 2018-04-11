package com.cdyoue.oddJobs.exception;

/**
 * Created by liaoyoule on 2017/4/20.
 */
public class LogicalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;
    private final String[] params;
    public LogicalException() {
        this.message = "对象不存在";
        this.params = null;
    }
    public LogicalException(String message, String... params) {
        super(message);
        this.message = message;
        this.params = params;
    }




    public ParameterizedErrorVM getErrorVM() {
        return new ParameterizedErrorVM(message, params);
    }
}
