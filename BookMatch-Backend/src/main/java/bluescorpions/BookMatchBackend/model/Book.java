package bluescorpions.BookMatchBackend.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Book {
	private String isbn;
	private String title;
	private Set<Author> authors;
	private String subject;
	
	@Id
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Book other = (Book) obj;
    return Objects.equals(authors, other.authors) && Objects.equals(isbn, other.isbn)
        && Objects.equals(subject, other.subject) && Objects.equals(title, other.title);
  }
  
  public String getSubject() {
		return subject;
  }
  
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
