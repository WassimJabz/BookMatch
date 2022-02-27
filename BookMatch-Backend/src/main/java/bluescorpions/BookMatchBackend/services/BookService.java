package bluescorpions.BookMatchBackend.services;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import bluescorpions.BookMatchBackend.dao.BookRepository;
import bluescorpions.BookMatchBackend.model.Author;
import bluescorpions.BookMatchBackend.model.Book;

@RestController
public class BookService {
    
    @Autowired
    BookRepository bookRepository;
    public static final Pattern VALID_COVER_URL_REGEX = 
            Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);
    
    public void CreateBook(String isbn, String title, Set<Author> Authors, String subject, String coverUrl) throws Exception{
     // Checking url
        Matcher matcher = VALID_COVER_URL_REGEX.matcher(coverUrl);
        if(!matcher.find()) throw new Exception("Invalid cover url");
        
        if(getBook(isbn) != null) throw new Exception("Book already exists in Database.");
        
        Book b = new Book();
        b.setIsbn(isbn);
        b.setTitle(title);
        b.setSubject(subject);
        b.setAuthors(Authors);
        b.setCoverUrl(coverUrl);
        bookRepository.save(b);
    } 
    public Book getBook(String isbn) {
      Book book = bookRepository.findByIsbn(isbn);
      return book;
    }
}
