package bluescorpions.BookMatchBackend.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Account {
    
    private String email;
    private String password;
    private String username;
    private String profilePicUrl;
    private List<Book> books;
    private Set<Account> mates;

    @Id
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getProfilePicUrl() {
        return profilePicUrl;
    }
    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @ManyToMany
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @ManyToMany
    public Set<Account> getMates() {
        return mates;
    }
    public void setMates(Set<Account> mates) {
        this.mates = mates;
    }
}
