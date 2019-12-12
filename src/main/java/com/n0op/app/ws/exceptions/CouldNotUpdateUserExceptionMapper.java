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
public class CouldNotUpdateUserExceptionMapper implements ExceptionMapper<CouldNotUpdateUserException> {


    @Override
    public Response toResponse(CouldNotUpdateUserException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),
                ErrorMessages.COULD_NOT_UPDATE_USER.name(), "http://google.com");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
