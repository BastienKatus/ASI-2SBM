package com.example.Notification_Websocket.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotificationController {


    @MessageMapping("/CARD")
    @SendTo("/topic/CARD")
    public String greeting(String message) throws Exception {
        String response = "response From TOPIC: CARD";
        return response;
    }

    @MessageMapping("/USER")
    @SendTo("/topic/USER")
    public String bye(String message) throws Exception {
        String response = "response From TOPIC: USER";
        return response;
    }
}
