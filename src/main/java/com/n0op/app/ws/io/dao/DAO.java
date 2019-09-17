package com.n0op.app.ws.io.dao;

import com.n0op.app.ws.shared.dto.RunDTO;
import com.n0op.app.ws.shared.dto.UserDTO;

import java.util.List;

/**
 * @author DanM
 */
public interface DAO {
    void openConnection();

    UserDTO getUserByName(String name);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO getUser(String id);
    List<UserDTO> getUsers(int start, int limit);
    void updateUser(UserDTO userDTO);
    void deleteUser(UserDTO userDTO);


    RunDTO getRunByName(String runName);
    RunDTO saveRun(RunDTO runDTO);
    RunDTO getRun(String id);
    List<RunDTO> getRuns(int start, int limit);
    void updateRun(RunDTO runDTO);
    void deleteRun(RunDTO runDTO);

    void closeConnection();
}
