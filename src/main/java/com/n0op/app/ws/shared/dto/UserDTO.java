package com.n0op.app.ws.shared.dto;

import java.io.Serializable;

/**
 * @author DanM
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -8598149725104180550L;
    private long id;

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
