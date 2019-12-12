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
public class UsernameAlreadyExistsExceptionMapper implements ExceptionMapper<UsernameAlreadyExistsException> {
    @Override
    public Response toResponse(UsernameAlreadyExistsException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),
                ErrorMessages.USERNAME_ALREADY_EXISTS.name(), "http://google.com");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
