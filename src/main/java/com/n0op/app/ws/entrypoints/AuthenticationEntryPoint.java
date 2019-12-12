package com.n0op.app.ws.entrypoints;

import com.n0op.app.ws.exceptions.NoRecordFoundException;
import com.n0op.app.ws.exceptions.UsernameAlreadyExistsException;
import com.n0op.app.ws.service.UsersService;
import com.n0op.app.ws.service.impl.UsersServiceImpl;
import com.n0op.app.ws.shared.dto.UserDTO;
import com.n0op.app.ws.ui.model.request.CreateUserRequestModel;
import com.n0op.app.ws.ui.model.request.UpdateUserRequestModel;
import com.n0op.app.ws.ui.model.response.*;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author DanM
 */
@Path("/authentication")
public class AuthenticationEntryPoint {
    Logger logger = Logger.getLogger(AuthenticationEntryPoint.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserProfileRest createUser(CreateUserRequestModel requestObject) {
        UserProfileRest returnValue = new UserProfileRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(requestObject, userDTO);

        UsersService usersService = new UsersServiceImpl();
        UserDTO createdUserProfile;
        boolean exists = true;

        try {
            usersService.getUserByUsername(userDTO.getUserName());
        } catch (NoRecordFoundException nre) {
            exists = false;
            logger.log(Level.INFO, "Record does not exist. Able to create as new.");
        }

        if(exists == false) {
            createdUserProfile = usersService.createUser(userDTO);
        } else {
            throw new UsernameAlreadyExistsException(ErrorMessages.USERNAME_ALREADY_EXISTS.getErrorMessage());
        }

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
    @Path("/userName/{userName}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest getUserByUsername(@PathParam("userName") String userName) {
        UserProfileRest returnValue;

        UsersService usersService = new UsersServiceImpl();
        UserDTO userDTO = usersService.getUserByUsername(userName);

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

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest updateUserDetails(@PathParam("id") String id, UpdateUserRequestModel updateUserRequestModel) {

        UsersService usersService = new UsersServiceImpl();
        UserDTO storedUser = usersService.getUser(id);
        BeanUtils.copyProperties(updateUserRequestModel, storedUser);

        usersService.updateUser(storedUser);

        UserProfileRest returnValue = new UserProfileRest();
        BeanUtils.copyProperties(storedUser, returnValue);

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
