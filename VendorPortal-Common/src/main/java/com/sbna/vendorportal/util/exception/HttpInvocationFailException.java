package com.sbna.vendorportal.util.exception;

public class HttpInvocationFailException extends Exception {

    private static final long serialVersionUID = 1997753363232807009L;

    public HttpInvocationFailException() {
    }

    public HttpInvocationFailException(String message) {
        super(message);
    }

    public HttpInvocationFailException(Throwable cause) {
        super(cause);
    }

    public HttpInvocationFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpInvocationFailException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
