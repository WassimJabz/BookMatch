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
    
    public void CreateAuthor(String name, String url) throws Exception{      
        
        // Checking name, url validity
        if(!name.matches("[a-zA-Z]+")) {
        	throw new Exception("Invalid name");
        }
        
        Matcher matcher = VALID_URL_REGEX.matcher(url);
        if(!matcher.find()) throw new Exception("Invalid url");
        
        Author auth = new Author();
        auth.setName(name);
        auth.setUrl(url);
        authorRepository.save(auth);
    }
    
    public void EditAuthor(String name, String url) throws Exception{
    	Author auth = getAuthor(name);
    	if(auth == null) throw new Exception("No author with this name in Database.");
    	//Check if new url valid
    	Matcher matcher = VALID_URL_REGEX.matcher(url);
        if(!matcher.find()) throw new Exception("Invalid url");
        authorRepository.delete(auth);
        auth.setUrl(url);
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
