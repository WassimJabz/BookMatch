package bluescorpions.BookMatchBackend.services;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import bluescorpions.BookMatchBackend.dao.AuthorRepository;
import bluescorpions.BookMatchBackend.model.Author;

@RestController
public class AuthorService {
    
    @Autowired
    AuthorRepository authorRepository;
    
    public static final Pattern VALID_URL_REGEX = 
            Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);
    
    public Author createAuthor(String name) throws Exception{      
        
        // Checking name, url validity
        if(!name.matches("[a-zA-Z]+")) {
        	throw new Exception("Invalid name");
        }
        
        Author auth = new Author();
        auth.setName(name);
        authorRepository.save(auth);
        return auth;
    }
    
    public void EditAuthor(String name) throws Exception{
    	Author auth = getAuthor(name);
    	if(auth == null) throw new Exception("No author with this name in Database.");
        authorRepository.delete(auth);
        authorRepository.save(auth);
    }
    
    public Author getAuthor(String name) {
      Optional<Author> author = authorRepository.findById(name);
      if(author.isPresent()) {
    	  return author.get();
      }
      else {
    	  return null;
      }
    }
}
