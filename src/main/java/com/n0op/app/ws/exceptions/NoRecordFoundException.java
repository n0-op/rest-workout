package com.n0op.app.ws.exceptions;

/**
 * @author DanM
 */
public class NoRecordFoundException extends RuntimeException {

    private static final long serialVersionUID = -2755457250451458321L;

    public NoRecordFoundException(String message) {
        super(message);
    }
}
