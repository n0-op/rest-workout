package com.n0op.app.ws.entrypoints;

import com.n0op.app.ws.service.WorkoutService;
import com.n0op.app.ws.service.impl.WorkoutServiceImpl;
import com.n0op.app.ws.shared.dto.RunDTO;
import com.n0op.app.ws.ui.model.request.CreateRunRequestModel;
import com.n0op.app.ws.ui.model.request.UpdateRunRequestModel;
import com.n0op.app.ws.ui.model.response.DeletedRunProfileResponseModel;
import com.n0op.app.ws.ui.model.response.RequestOperation;
import com.n0op.app.ws.ui.model.response.ResponseStatus;
import com.n0op.app.ws.ui.model.response.RunProfileRest;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DanM
 */
@Path("/runs")
public class RunsEntryPoint {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RunProfileRest createRun(CreateRunRequestModel requestObject) {
        RunProfileRest returnValue = new RunProfileRest();

        RunDTO runDTO = new RunDTO();
        BeanUtils.copyProperties(requestObject, runDTO);

        WorkoutService workoutService = new WorkoutServiceImpl();
        RunDTO createdRunProfile = workoutService.createRun(runDTO);

        BeanUtils.copyProperties(createdRunProfile, returnValue);
        return returnValue;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RunProfileRest getRunProfile(@PathParam("id") String id) {
        RunProfileRest returnValue = null;

        WorkoutService workoutService = new WorkoutServiceImpl();
        RunDTO runProfile = workoutService.getRun(id);

        returnValue = new RunProfileRest();
        BeanUtils.copyProperties(runProfile, returnValue);

        return returnValue;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<RunProfileRest> getRunningWorkouts(@DefaultValue("0") @QueryParam("Start") int start,
                                                   @DefaultValue("50") @QueryParam("limit") int limit) {
        WorkoutService workoutService = new WorkoutServiceImpl();
        List<RunDTO> runs = workoutService.getRuns(start,limit);

        // Prepare return value
        List<RunProfileRest> returnValue = new ArrayList<>();
        for(RunDTO runDto : runs) {
            RunProfileRest runModel = new RunProfileRest();
            BeanUtils.copyProperties(runDto, runModel);
            returnValue.add(runModel);
        }

        return returnValue;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RunProfileRest updateRunDetails(@PathParam("id") String id,
           UpdateRunRequestModel updateRunRequestModel) {

        WorkoutService workoutService = new WorkoutServiceImpl();
        RunDTO storedRun = workoutService.getRun(id);
        BeanUtils.copyProperties(updateRunRequestModel,storedRun);

        workoutService.updateRun(storedRun);

        RunProfileRest returnValue = new RunProfileRest();
        BeanUtils.copyProperties(storedRun, returnValue);

        return returnValue;
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeletedRunProfileResponseModel deleteRunProfile(@PathParam("id") String id) {
        DeletedRunProfileResponseModel returnValue = new DeletedRunProfileResponseModel();
        returnValue.setRequestOperation(RequestOperation.DELETE);

        WorkoutService workoutService = new WorkoutServiceImpl();
        RunDTO storedRunDetails = workoutService.getRun(id);

        workoutService.deleteRun(storedRunDetails);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }


}
