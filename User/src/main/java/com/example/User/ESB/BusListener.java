package com.example.User.ESB;

import com.example.User.Controller.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class BusListener {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    UserService userService;


    @JmsListener(destination = "USER", containerFactory = "connectionFactory")
    public void receiveMessage(BusModel busmodel, Message message) {
        System.out.println("[BUSLISTENER] [CHANNEL RESULT_BUS_MNG] RECEIVED String MSG=["+busmodel.toString()+"]");

        switch (busmodel.getAction()){
            case CREATE -> userService.addUser(busmodel.getUserModel());
            case DELETE -> userService.deleteUser(busmodel.getUserModel().getId());
            case UPDATE -> userService.updateUser(busmodel.getUserModel());
        }

        //System.out.println("[BUSLISTENER] [CHANNEL RESULT_BUS_MNG] RECEIVED String MSG=["+busmodel.toString()+"]");

    }

}
