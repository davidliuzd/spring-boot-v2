package net.liuzd.spring.boot.v2.conteroller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import net.liuzd.spring.boot.v2.domain.Message;
import net.liuzd.spring.boot.v2.domain.User;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/greetings")
    public User greeting(Message message) throws Exception {
        Thread.sleep(1000);
        return new User("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
