package com.n0op.app.ws.exceptions;

import com.n0op.app.ws.ui.model.response.ErrorMessage;
import com.n0op.app.ws.ui.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author DanM
 */
@Provider
public class NoWorkoutFoundExceptionMapper implements ExceptionMapper<NoRecordFoundException> {

    public Response toResponse(NoRecordFoundException exception){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
        ErrorMessages.NO_RECORD_FOUND.name(), "https://wwww.google.com/");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
