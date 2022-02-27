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
    
    
    public Author createAuthor(String name) throws Exception{      
        
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
