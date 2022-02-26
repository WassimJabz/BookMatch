package bluescorpions.BookMatchBackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import bluescorpions.BookMatchBackend.dao.AccountRepository;
import bluescorpions.BookMatchBackend.model.ChatMessage;

@Component
public class WebSocketChatEventListener {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    @Autowired
    private AccountRepository accountRepository;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        System.out.println("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String email = (String) headerAccessor.getSessionAttributes().get("email");
        if(email != null) {

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType("Leave");
            chatMessage.setSender(accountRepository.findByEmail(email));

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
