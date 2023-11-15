package com.example.ESB;

import com.example.Controller.CardModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Message;

@Component
public class BusListener {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    CardModelService cardModelService;


    @JmsListener(destination = "CARD", containerFactory = "connectionFactory")
    public void receiveMessage(BusModel busmodel, Message message) {

        switch (busmodel.getAction()){
            case CREATE -> cardModelService.createCard(busmodel.getCardModel());
            case DELETE -> cardModelService.deleteCard(busmodel.getCardModel().getId());
            case UPDATE -> cardModelService.updateCard(busmodel.getCardModel());
        }

        System.out.println("[BUSLISTENER] [CHANNEL RESULT_BUS_MNG] RECEIVED String MSG=["+busmodel.toString()+"]");

    }

    @JmsListener(destination = "A", containerFactory = "connectionFactory")
    public void receiveMessageA(String msgStr, Message message) {

        System.out.println("[BUSLISTENER] [CHANNEL A] RECEIVED String MSG=["+msgStr+"]");

    }

    @JmsListener(destination = "B", containerFactory = "connectionFactory")
    public void receiveMessageB(String msgStr, Message message) {

        System.out.println("[BUSLISTENER] [CHANNEL B] RECEIVED String MSG=["+msgStr+"]");

    }
}
