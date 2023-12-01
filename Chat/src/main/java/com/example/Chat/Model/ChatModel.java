package com.example.Chat.Model;

import com.example.CommonLib.ChatDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ChatModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String message;
	private int idSender;
	private int idReceiver;
	private int idRoom;
	private int numMessage;
	public ChatModel() {
		this.message = "";
		this.idSender = 0;
		this.idReceiver = 0;
		this.idRoom = 0;

	}

	public ChatModel(String message, int idSender, int idReceiver, int idRoom, int numMessage) {
		this.message = message;
		this.idSender = idSender;
		this.idReceiver = idReceiver;
		this.idRoom = idRoom;
		this.numMessage = numMessage;
	}

	public ChatModel(ChatDTO chat) {
		this.message = chat.getMessage();
		this.idSender = chat.getIdSender();
		this.idReceiver = chat.getIdReceiver();
		this.idRoom = chat.getIdRoom();
		this.numMessage = chat.getNumMessage();
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

	@Override
	public String toString() {
		return "ChatModel{" +
				"id=" + id +
				", message='" + message + '\'' +
				", idSender=" + idSender +
				", idReceiver=" + idReceiver +
				", idRoom=" + idRoom +
				", numMessage=" + numMessage +
				'}';
	}
}
