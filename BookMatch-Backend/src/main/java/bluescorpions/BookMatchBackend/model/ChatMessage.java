package bluescorpions.BookMatchBackend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ChatMessage {
  
  private String type;
  private String content;
  private Account sender;
  private Account receiver;
  private ChatRoom chatRoom;
  private String id; 
  
  @ManyToOne(fetch = FetchType.EAGER)
  public ChatRoom getChatRoom() {
    return chatRoom;
  }

  public void setChatRoom(ChatRoom chatRoom) {
    this.chatRoom = chatRoom;
  }

  @Id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public String getContent() {
      return content;
  }

  public void setContent(String content) {
      this.content = content;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  public Account getSender() {
    return sender;
  }

  public void setSender(Account sender) {
    this.sender = sender;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  public Account getReceiver() {
    return receiver;
  }

  public void setReceiver(Account receiver) {
    this.receiver = receiver;
  }

}
