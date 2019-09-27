package com.n0op.app.ws.exceptions;

/**
 * @author DanM
 */
public class CouldNotDeleteUserException extends RuntimeException {

    private static final long serialVersionUID = 2553166120191494559L;

    public CouldNotDeleteUserException(String message) {super(message); }
}
