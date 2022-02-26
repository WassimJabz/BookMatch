package bluescorpions.BookMatchBackend.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Account> getMates() {
        return mates;
    }
    public void setMates(Set<Account> mates) {
        this.mates = mates;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (books == null) {
            if (other.books != null)
                return false;
        } else if (!books.equals(other.books))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (mates == null) {
            if (other.mates != null)
                return false;
        } else if (!mates.equals(other.mates))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (profilePicUrl == null) {
            if (other.profilePicUrl != null)
                return false;
        } else if (!profilePicUrl.equals(other.profilePicUrl))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    
}
