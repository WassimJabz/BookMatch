package bluescorpions.BookMatchBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {
  
  private String name;
  private String url;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @Id
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  
  
}
