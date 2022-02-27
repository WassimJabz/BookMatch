package bluescorpions.BookMatchBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.Message;
import bluescorpions.BookMatchBackend.services.AccountService;

@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    private AccountService accountController;
    
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message) {
        
      Account account = accountController.getAccount(to);
        
      if(account != null) {
          simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
      }
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    public Message newUser(@Payload Message webSocketChatMessage,
            SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
        return webSocketChatMessage;
    }

}