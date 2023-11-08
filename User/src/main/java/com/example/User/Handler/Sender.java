package com.example.User.Handler;

import com.example.User.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Sender {

    private final JmsTemplate jmsTemplate;

    private static final String QUEUE_KEY = "spring-messaging.queue.name";

    private String queue;

    private final Environment environment;

    @Autowired
    public Sender(JmsTemplate jmsTemplate, Environment environment) {
        this.jmsTemplate = jmsTemplate;
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        queue = environment.getProperty(QUEUE_KEY);
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public void sendMessage(UserModel user) {

        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending an personne message.");
        jmsTemplate.convertAndSend(queue, user);
    }
}
