package com.example.User.Handler;

import com.example.User.Model.UserModel;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(Receiver.class);
    private final Sender sender;

    @Autowired
    public Receiver(Sender sender) {
        this.sender = sender;
    }

    @JmsListener(destination = "${spring-messaging.queue.name}")
    public void receiveMessage(UserModel user) throws InterruptedException {
        log.info("Hello my giga friend: " + user.getId());

        //TODO
    }
}
