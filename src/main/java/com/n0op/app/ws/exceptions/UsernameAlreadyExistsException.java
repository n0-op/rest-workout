package com.n0op.app.ws.exceptions;

/**
 * @author DanM
 */
public class UsernameAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -930900525294054500L;

    public UsernameAlreadyExistsException(String message) {super(message); }
}
