package com.example.Notification_Websocket.Controller;


import com.example.Notification_Websocket.Receiver.Greeting;
import com.example.Notification_Websocket.Sender.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotificationController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/bye")
    @SendTo("/topic/bye")
    public Greeting bye(HelloMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        return new Greeting("Bye, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
