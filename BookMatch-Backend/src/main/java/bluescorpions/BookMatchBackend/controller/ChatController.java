package bluescorpions.BookMatchBackend.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import bluescorpions.BookMatchBackend.dao.ChatRoomRepository;
import bluescorpions.BookMatchBackend.model.ChatMessage;
import bluescorpions.BookMatchBackend.service.ChatRoomService;

@Controller
public class ChatController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Autowired
  private ChatRoomService chatRoomService;
  
  @Autowired
  private ChatRoomRepository chatRoomRepository;

  // @MessageMapping("/chat.sendMessage")
  // @SendTo("/topic/javainuse")
  // public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
  // return chatMessage;
  // }
  //
  // @MessageMapping("/chat.newUser")
  // @SendTo("/topic/javainuse")
  // public ChatMessage newUser(@Payload ChatMessage chatMessage,
  // SimpMessageHeaderAccessor headerAccessor) {
  // headerAccessor.getSessionAttributes().put("email", chatMessage.getSender().getEmail());
  // return chatMessage;
  // }

  @MessageMapping("/chat")
  public void processMessage(@Payload ChatMessage chatMessage) {

    Optional<String> chatRoomId =
        chatRoomService.getChatRoomId(chatMessage.getSender(), chatMessage.getReceiver());
    
    if(chatRoomId.isEmpty()) {
      String roomId = chatRoomService.createChatRoomId(chatMessage.getSender(), chatMessage.getReceiver());
      chatMessage.setChatRoom(chatRoomRepository.findById(roomId).get());
    }
    else {
      chatMessage.setChatRoom(chatRoomRepository.findById(chatRoomId.get()).get());
    }
  }

}
