package com.example.Notification_Websocket.ESB;

import com.example.CommonLib.BusNotifModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BusListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "NOTIFS", containerFactory = "connectionFactory")
    public void receiveMessage(BusNotifModel busModel, Message message) {
        System.out.println("[BUSLISTENER] [CHANNEL NOTIFS] RECEIVED String MSG=["+message.toString()+"]");
        String responseToSend = new SimpleDateFormat("HH:mm").format(new Date()).toString();
        String topic = "/topic/"+busModel.getTopic();
        messagingTemplate.convertAndSend(topic, responseToSend);
    }
}