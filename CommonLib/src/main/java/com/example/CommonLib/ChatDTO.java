package com.example.CommonLib;

import java.util.HashSet;
import java.util.Set;

public class ChatDTO {
    private Integer id;
    private String message;
    private int idSender;
    private int idReceiver;
    private int numMessage;
    private int idRoom;
    public ChatDTO() {
    }

    public ChatDTO(String message, int idSender, int idReceiver, int numMessage, int idRoom) {
        this.message = message;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.numMessage = numMessage;
        this.idRoom = idRoom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getNumMessage() {
        return numMessage;
    }

    public void setNumMessage(int numMessage) {
        this.numMessage = numMessage;
    }
}
