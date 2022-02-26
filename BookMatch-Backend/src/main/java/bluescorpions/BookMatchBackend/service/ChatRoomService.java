package bluescorpions.BookMatchBackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bluescorpions.BookMatchBackend.dao.ChatRoomRepository;
import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.ChatRoom;

@Service
public class ChatRoomService {
  
  @Autowired
  private ChatRoomRepository chatRoomRepository;
  
  public Optional<String> getChatRoomId(Account account1, Account account2){
    
    if(account1.getEmail().compareTo(account2.getEmail()) > 0) {
      Account temp = account1;
      account1 = account2;
      account2 = temp;
    }
    
    List<ChatRoom> retrievedRoom = chatRoomRepository.findByAccount1AndAccount2(account1, account2);
    
    if(retrievedRoom.size() >= 1) {
      return Optional.of(retrievedRoom.get(0).getId());
    }
    
    else {
      return Optional.empty();
    }
  }
  
  // Returns true if had to create, false if already exists
  public String createChatRoomId(Account account1, Account account2) {
    
    if(account1.getEmail().compareTo(account2.getEmail()) > 0) {
      Account temp = account1;
      account1 = account2;
      account2 = temp;
    }
    
    Optional<String> retrievedId = getChatRoomId(account1, account2);
    
    if(retrievedId.isEmpty()) {
      ChatRoom room = new ChatRoom();
      room.setAccount1(account1);
      room.setAccount2(account2);
      room.setId(UUID.randomUUID().toString());
      chatRoomRepository.save(room);
      return room.getId();
    }
    
    else {
      return retrievedId.get();
    }
  }
  
  
}
