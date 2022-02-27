package bluescorpions.BookMatchBackend.model;

public class Message {
  private String type;
  private String content;
  private String sender;
  private String receiver;

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

  public String getSender() {
      return sender;
  }

  public void setSender(String sender) {
      this.sender = sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
  
  
}
