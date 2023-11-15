package com.example.ESB;

import com.example.CommonLib.BusNotifModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionFactory;

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

    public void sendMsg(BusNotifModel busNotifModel, String busName) {
        System.out.println("[BUSSERVICE] SEND String MSG=["+busNotifModel.toString()+"] to Bus=["+busName+"]");
        jmsTemplate.convertAndSend(busName,busNotifModel);
    }
}
