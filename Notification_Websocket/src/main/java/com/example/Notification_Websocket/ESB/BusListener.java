package com.example.Notification_Websocket.ESB;

import com.example.CommonLib.BusNotifModel;
import com.example.Notification_Websocket.Receiver.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class BusListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "NOTIFS", containerFactory = "connectionFactory")
    public void receiveMessage(BusNotifModel busModel, Message message) {
        System.out.println("[BUSLISTENER] [CHANNEL NOTIFS] RECEIVED String MSG=["+message.toString()+"]");
        // Envoyez un message à la websocket connectée du client
        messagingTemplate.convertAndSendToUser("Userbonjour", "/topic/greetings", new Greeting("Votre opération est terminée!"));
    }
}