package com.n0op.app.ws.exceptions;

/**
 * @author DanM
 */
public class MissingRequiredFieldsException extends RuntimeException {

    private static final long serialVersionUID = 5769993467180992107L;

    public MissingRequiredFieldsException(String message) {super(message);};
}
