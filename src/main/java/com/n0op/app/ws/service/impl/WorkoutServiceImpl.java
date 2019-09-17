package com.n0op.app.ws.service.impl;

import com.n0op.app.ws.exceptions.CouldNotDeleteWorkoutException;
import com.n0op.app.ws.exceptions.CouldNotUpdateRecordException;
import com.n0op.app.ws.exceptions.NoRecordFoundException;
import com.n0op.app.ws.io.dao.DAO;
import com.n0op.app.ws.io.dao.impl.MySQLDAO;
import com.n0op.app.ws.service.WorkoutService;
import com.n0op.app.ws.shared.dto.RunDTO;
import com.n0op.app.ws.ui.model.response.ErrorMessages;
import com.n0op.app.ws.utils.RunWorkoutUtils;

import java.util.List;

/**
 * @author DanM
 */
public class WorkoutServiceImpl implements WorkoutService {

    private DAO database;

    public WorkoutServiceImpl() {
        this.database = new MySQLDAO();
    }

    private RunWorkoutUtils runWorkoutUtils = new RunWorkoutUtils();

    @Override
    public RunDTO createRun(RunDTO runDTO) {
        RunDTO returnValue;

        runWorkoutUtils.validateRunWorkoutRequiredFields(runDTO);

        //Generate secure public used id
        String runId = runWorkoutUtils.generateUserId(30);
        runDTO.setRunId(runId);

        returnValue = this.saveRun(runDTO);

        return returnValue;
    }



    @Override
    public RunDTO getRun(String id) {
        RunDTO returnValue;

        try{
            this.database.openConnection();
            returnValue = this.database.getRun(id);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        finally{
            this.database.closeConnection();
        }

        return returnValue;
    }

    @Override
    public RunDTO getRunByName(RunDTO runDTO) {
        return null;
    }

    @Override
    public List<RunDTO> getRuns(int start, int limit) {
        List<RunDTO> runs;

        // Get runs from DB
        try {
            this.database.openConnection();
            runs = this.database.getRuns(start, limit);

        } finally {
            this.database.closeConnection();
        }

        return runs;
    }

    @Override
    public void updateRun(RunDTO runDTO) {

        try {
            this.database.openConnection();
            this.database.updateRun(runDTO);
        } catch (Exception ex) {
            throw new CouldNotUpdateRecordException(ex.getMessage());
        } finally {
            this.database.closeConnection();
        }
    }

    @Override
    public void deleteRun(RunDTO runDTO) {
        try {
            this.database.openConnection();
            this.database.deleteRun(runDTO);
        } catch (Exception ex) {
            throw new CouldNotDeleteWorkoutException(ex.getMessage());
        } finally {
            this.database.closeConnection();
        }
    }

     private RunDTO saveRun(RunDTO runDTO) {
        RunDTO returnValue = null;

        try {
            this.database.openConnection();
            returnValue = this.database.saveRun(runDTO);
        } finally {
            this.database.closeConnection();
        }
        return returnValue;
    }
}
