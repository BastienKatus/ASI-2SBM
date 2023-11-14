package com.example.User.ESB;

import com.example.User.Model.UserModel;

import java.io.Serializable;

public class BusModel implements Serializable {
    private UserModel userModel;
    private BusAction action;

    public BusModel( ) {
        this.userModel = new UserModel();
        this.action = BusAction.DELETE;
    }

    public BusModel(UserModel cardModel, BusAction action) {
        this.userModel = cardModel;
        this.action = action;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public BusAction getAction() {
        return action;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setAction(BusAction action) {
        this.action = action;
    }
    @Override
    public String toString() {
        return "BusModel{" +
                "userModel=" + userModel +
                ", action=" + action +
                '}';
    }
}
