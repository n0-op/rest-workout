package com.n0op.app.ws.exceptions;

/**
 * @author DanM
 */
public class CouldNotDeleteWorkoutException extends RuntimeException {
    private static final long serialVersionUID = 3706787479905039634L;

    public CouldNotDeleteWorkoutException(String message) { super(message); }
}
