package com.n0op.app.ws.service;

import com.n0op.app.ws.shared.dto.RunDTO;

import java.util.List;

public interface WorkoutService {
    RunDTO createRun(RunDTO runDTO);
    RunDTO getRun(String id);
    RunDTO getRunByName(RunDTO runDTO);
    List<RunDTO> getRuns(int start, int limit);
    void updateRun(RunDTO runDTO);
    void deleteRun(RunDTO runDTO);

}
