package com.exchange.backend.persistence.domain;

/**
 * Created by greenlucky on 4/17/17.
 */
public class UserPassword {

    private String password;

    private String newPassword;

    private String confirmNewPassword;

    public UserPassword() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
