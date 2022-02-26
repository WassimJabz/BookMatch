package bluescorpions.BookMatchBackend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ChatRoom {
  
  // Sorted by alphabetical order (Account 1 always comes before 2 in alphabetical)
  private Account account1;
  private Account account2;
  private String id;
  
  @ManyToOne(fetch = FetchType.EAGER)
  public Account getAccount1() {
    return account1;
  }
  public void setAccount1(Account account1) {
    this.account1 = account1;
  }
  
  @ManyToOne(fetch = FetchType.EAGER)
  public Account getAccount2() {
    return account2;
  }
  public void setAccount2(Account account2) {
    this.account2 = account2;
  }
  
  @Id
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
}
