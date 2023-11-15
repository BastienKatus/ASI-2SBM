package com.example.ESB;

import com.example.Model.CardModel;

import java.io.Serializable;

public class BusNotifModel implements Serializable {
    private Integer idTransaction;
    private String topic;


    public void setIdOperation(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getIdOperation() {
        return idTransaction;
    }

    public String getTopic() {
        return topic;
    }

    public BusNotifModel(Integer idTransaction, String topic) {
        this.idTransaction = idTransaction;
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "BusNotifModel{" +
                "idOperation=" + idTransaction +
                ", topic='" + topic + '\'' +
                '}';
    }

}