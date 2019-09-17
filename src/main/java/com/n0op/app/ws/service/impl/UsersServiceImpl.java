package com.n0op.app.ws.service.impl;

import com.n0op.app.ws.io.dao.DAO;
import com.n0op.app.ws.io.dao.impl.MySQLDAO;
import com.n0op.app.ws.service.UsersService;
import com.n0op.app.ws.shared.dto.UserDTO;
import com.n0op.app.ws.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author DanM
 */
public class UsersServiceImpl implements UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    private DAO database;

    public UsersServiceImpl() {
        this.database = new MySQLDAO();
    }

    private UserUtils userUtils = new UserUtils();
    private int saltLength = 30;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserDTO returnValue;

        userUtils.validateRunWorkoutRequiredFields(userDTO);

        String userId = userUtils.generateUserId(30);
        userDTO.setUserId(userId);

        String salt = userUtils.generateSalt(saltLength).get();

        userDTO.setSalt(salt);

        logger.debug("Salt Generated: " + salt);
        System.out.println("Salt Generated: " + salt);

        returnValue = this.saveUser(userDTO);

        return returnValue;
    }

    @Override
    public UserDTO getUser(String id) {
        return null;
    }

    @Override
    public UserDTO getUserByName(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> getUsers(int start, int limit) {
        return null;
    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }

    @Override
    public void deleteUser(UserDTO userDTO) {

    }

    private UserDTO saveUser(UserDTO userDTO) {
        UserDTO returnValue = null;

        try {
            this.database.openConnection();
            returnValue = this.database.saveUser(userDTO);
        } finally {
            this.database.closeConnection();
        }
        return returnValue;
    }
}
