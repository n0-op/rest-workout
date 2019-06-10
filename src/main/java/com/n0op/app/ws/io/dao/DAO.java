package com.n0op.app.ws.io.dao;

import com.n0op.app.ws.shared.dto.RunDTO;

import java.util.List;

/**
 * @author DanM
 */
public interface DAO {
    void openConnection();
    RunDTO getRunByName(String runName);
    RunDTO saveRun(RunDTO runDTO);
    RunDTO getRun(String id);
    List<RunDTO> getRuns(int start, int limit);
    void updateRun(RunDTO runDTO);
    void deleteRun(RunDTO runDTO);
    void closeConnection();
}
