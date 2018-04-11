package com.cdyoue.oddJobs.exception;

import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;

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
public class UnAuthenticationMessageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String description;
    private final Integer code;


    public UnAuthenticationMessageException(AuthenticationMessage accountregisternameerror) {
        this.code = accountregisternameerror.getCode();
        this.message = accountregisternameerror.getMsg();
        this.description = accountregisternameerror.getDescription();
    }

    public MessageErrorVM getErrorVM() {
        return new MessageErrorVM(code,message, description);
    }



}
