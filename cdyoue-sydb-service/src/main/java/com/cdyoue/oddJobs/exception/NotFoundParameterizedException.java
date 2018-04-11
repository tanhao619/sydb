package com.cdyoue.oddJobs.exception;

/**
 * Created by honshe on 2017/3/1.
 * 对象没有找到
 */
public class NotFoundParameterizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String message;
    private final String[] params;

    public NotFoundParameterizedException(String message, String... params) {
        super(message);
        this.message = message;
        this.params = params;
    }

    public ParameterizedErrorVM getErrorVM() {
        return new ParameterizedErrorVM(message, params);
    }
}
