package com.n0op.app.ws.entrypoints;

import com.n0op.app.ws.service.UsersService;
import com.n0op.app.ws.service.impl.UsersServiceImpl;
import com.n0op.app.ws.shared.dto.UserDTO;
import com.n0op.app.ws.ui.model.request.CreateUserRequestModel;
import com.n0op.app.ws.ui.model.response.UserProfileRest;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author DanM
 */
@Path("/authentication")
public class AuthenticationEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserProfileRest createUser(CreateUserRequestModel requestObject) {
        UserProfileRest returnValue = new UserProfileRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(requestObject, userDTO);

        UsersService usersService = new UsersServiceImpl();
        UserDTO createdUserProfile = usersService.createUser(userDTO);

        BeanUtils.copyProperties(createdUserProfile, returnValue);
        return returnValue;
    }

}
