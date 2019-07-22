package com.n0op.app.ws.exceptions;

import java.io.Serializable;

/**
 * @author DanM
 */
public class CouldNotUpdateRecordException extends RuntimeException {

    private static final long serialVersionUID = -7031658990457451295L;

    public CouldNotUpdateRecordException(String message) {super(message);}
}
