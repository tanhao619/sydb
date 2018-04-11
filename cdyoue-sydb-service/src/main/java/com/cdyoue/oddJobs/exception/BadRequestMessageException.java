package com.cdyoue.oddJobs.exception;

import com.cdyoue.oddJobs.dto.common.HttpMessage;

/**
 * Custom, parameterized exception, which can be translated on the client side.
 * For example:
 *
 * <pre>
 * throw new CustomParameterizedException(&quot;myCustomError&quot;, &quot;hello&quot;, &quot;world&quot;);
 * </pre>
 *
 * Can be translated with:
 *
 * <pre>
 * "error.myCustomError" :  "The server says {{params[0]}} to {{params[1]}}"
 * </pre>
 */
public class BadRequestMessageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String description;
    private final Integer code;


    public BadRequestMessageException(HttpMessage message) {
        this.code = message.getCode();
        this.message = message.getMsg();
        this.description = message.getDescription();
    }

    public MessageErrorVM getErrorVM() {
        return new MessageErrorVM(code,message, description);
    }

}
