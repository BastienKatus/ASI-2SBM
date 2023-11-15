package com.example.User.ESB;

import com.example.User.Model.UserModel;

import java.io.Serializable;

public class BusModel implements Serializable {
    private UserModel userModel;
    private BusAction action;
    private Integer idTransaction;
    public BusModel( ) {
        this.userModel = new UserModel();
        this.action = BusAction.DELETE;
        this.idTransaction = 0;
    }

    public BusModel(UserModel cardModel, BusAction action,Integer idTransaction) {
        this.userModel = cardModel;
        this.action = action;
        this.idTransaction = idTransaction ;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public BusAction getAction() {
        return action;
    }


    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public String toString() {
        return "BusModel{" +
                "userModel=" + userModel +
                ", action=" + action +
                ", idTransaction=" + idTransaction +
                '}';
    }
}
