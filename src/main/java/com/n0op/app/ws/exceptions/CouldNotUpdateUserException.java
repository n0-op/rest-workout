package com.n0op.app.ws.exceptions;

/**
 * @author DanM
 */
public class CouldNotUpdateUserException extends RuntimeException {

    private static final long serialVersionUID = -8029321605634418027L;

    public CouldNotUpdateUserException(String message) {
        super(message);
    }
}
