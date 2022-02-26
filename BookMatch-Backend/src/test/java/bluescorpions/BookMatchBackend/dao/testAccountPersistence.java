package bluescorpions.BookMatchBackend.dao;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.Author;
import bluescorpions.BookMatchBackend.model.Book;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class testAccountPersistence {
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void testFindBy(){

        String email = "test@email.com";
        String password = "pass";
        String profilePicUrl = "test.imgur.com";
        String username = "test";

        String title = "testbook";
        String subject = "Novel";
        String coverUrl = "test.com";
        String isbn = "32342433423";
        
        String authorName = "Wassim";
        String authorUrl = "test.imgur.fr";
        Author author = new Author();
        author.setName(authorName);
        author.setUrl(authorUrl);
        
        String authorName2 = "Enzo";
        String authorUrl2 = "test.imgur.fr";
        Author author2 = new Author();
        author2.setName(authorName2);
        author2.setUrl(authorUrl2);;
        
        HashSet<Author> authors = new HashSet<Author>();
        authors.add(author2);
        authors.add(author);
        
        Book book = new Book();
        book.setTitle(title);
        book.setSubject(subject);
        book.setCoverUrl(coverUrl);
        book.setIsbn(isbn);
        book.setAuthors(authors);
        
        HashSet<Book> books = new HashSet<Book>();
        books.add(book);
        
        HashSet<Account> mates = new HashSet<Account>();
        
        Account user = new Account();
        user.setEmail(email);
        user.setPassword(password);
        user.setProfilePicUrl(profilePicUrl);
        user.setUsername(username);
        user.setBooks(books);
        user.setMates(mates);
        
        authorRepository.save(author);
        authorRepository.save(author2);
        bookRepository.save(book);
        accountRepository.save(user);
       
        accountRepository.deleteById(email);
        
        assertNull(accountRepository.findByEmail(email));
    }
}
