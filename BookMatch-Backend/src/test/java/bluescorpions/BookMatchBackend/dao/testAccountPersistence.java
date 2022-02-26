package bluescorpions.BookMatchBackend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.Book;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class testAccountPersistence {
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookRepository bookRepository;

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
        ArrayList<String> authors = new ArrayList<String>();
        authors.add("wassim");
        authors.add("enzo");

        Account user = new Account();
        user.setEmail(email);
        user.setPassword(password);
        user.setProfilePicUrl(profilePicUrl);
        user.setUsername(username);

        Book book = new Book();
        book.setTitle(title);
        book.setSubject(subject);
        book.setCoverUrl(coverUrl);
        book.setIsbn(isbn);
        book.setAuthors(authors);

        bookRepository.save(book);
        accountRepository.save(user);
    

        Book bookRetrieved = bookRepository.findByIsbn(isbn);
        Account userRetrieved = accountRepository.findByEmail(email);

        assertEquals(book, bookRetrieved);
        assertEquals(user, userRetrieved);
    }
}
