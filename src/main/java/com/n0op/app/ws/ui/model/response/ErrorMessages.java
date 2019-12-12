package com.n0op.app.ws.ui.model.response;

/**
 * @author DanM
 */
public enum ErrorMessages {
    NO_RECORD_FOUND("Record with provided info was not found"),
    COULD_NOT_DELETE_WORKOUT("Could not delete workout"),
    MISSING_REQUIRED_FIELDS("Missing Required Fields"),
    COULD_NOT_DELETE_USER("Could not delete user"),
    COULD_NOT_UPDATE_USER("Could not update user"),
    COULD_NOT_UPDATE_WORKOUT("Could not update workout"),
    USERNAME_ALREADY_EXISTS("Username already exists");

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
