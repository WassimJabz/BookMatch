package bluescorpions.BookMatchBackend.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {
  
  private String name;
  
  @Id
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Author other = (Author) obj;
    return Objects.equals(name, other.name);
  }
  
  
  
  
  
  
}
