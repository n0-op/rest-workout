package com.n0op.app.ws.entrypoints;

import com.n0op.app.ws.service.UsersService;
import com.n0op.app.ws.service.impl.UsersServiceImpl;
import com.n0op.app.ws.shared.dto.UserDTO;
import com.n0op.app.ws.ui.model.request.CreateUserRequestModel;
import com.n0op.app.ws.ui.model.response.DeletedObjectProfileResponseModel;
import com.n0op.app.ws.ui.model.response.RequestOperation;
import com.n0op.app.ws.ui.model.response.ResponseStatus;
import com.n0op.app.ws.ui.model.response.UserProfileRest;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest getUser(@PathParam("id") String id){
        UserProfileRest returnValue;

        UsersService usersService = new UsersServiceImpl();
        UserDTO userDTO = usersService.getUser(id);

        returnValue = new UserProfileRest();
        BeanUtils.copyProperties(userDTO, returnValue);

        return returnValue;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<UserProfileRest> getUsers(@DefaultValue("0") @QueryParam("Start") int start,
                                          @DefaultValue("50") @QueryParam("limit") int limit){
        UsersService usersService = new UsersServiceImpl();
        List<UserDTO> users = usersService.getUsers(start, limit);

        List<UserProfileRest> returnValue = new ArrayList<>();

        for(UserDTO userDTO : users) {
            UserProfileRest userProfileRest = new UserProfileRest();
            BeanUtils.copyProperties(userDTO, userProfileRest);
            returnValue.add(userProfileRest);
        }

        return returnValue;
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeletedObjectProfileResponseModel deleteUserProfile(@PathParam("id") String id){
        DeletedObjectProfileResponseModel returnValue = new DeletedObjectProfileResponseModel();
        returnValue.setRequestOperation(RequestOperation.DELETE);

        UsersService usersService = new UsersServiceImpl();
        UserDTO storedUser = usersService.getUser(id);

        usersService.deleteUser(storedUser);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }
}
