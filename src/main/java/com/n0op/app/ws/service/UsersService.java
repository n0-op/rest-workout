package com.n0op.app.ws.service;

import com.n0op.app.ws.io.entity.UserEntity;
import com.n0op.app.ws.shared.dto.UserDTO;

import java.util.List;

/**
 * @author DanM
 */
public interface UsersService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(String id);
    UserDTO getUserByUsername(String userName);
    List<UserDTO> getUsers(int start, int limit);
    void updateUser(UserDTO userDTO);
    void deleteUser(UserDTO userDTO);
}

