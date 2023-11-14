package com.example.User.ESB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class BusEmitter {

    @Autowired
    JmsTemplate jmsTemplate ;


    public void sendMsg(BusModel busModel) {
        System.out.println("[BUSSERVICE] SEND String MSG=["+busModel.getAction() + busModel.toString());
        jmsTemplate.convertAndSend("RESULT_BUS_MNG",busModel);
    }

    public void sendMsg(BusModel busModel, String busName) {
        System.out.println("[BUSSERVICE] SEND String MSG=["+busModel.getAction() + busModel.toString()+"] to Bus=["+busName+"]");
        jmsTemplate.convertAndSend(busName,busModel);
    }

}
