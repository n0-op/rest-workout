package com.n0op.app.ws.ui.model.response;

/**
 * @author DanM
 */
public enum ErrorMessages {
    NO_RECORD_FOUND("Record with provided id is not found"),
    COULD_NOT_DELETE_WORKOUT("Could not delete workout");

    public String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
