package bluescorpions.BookMatchBackend.services;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import bluescorpions.BookMatchBackend.dao.AccountRepository;
import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.Book;

@RestController
public class AccountService {
    
    @Autowired
    AccountRepository accountRepository;
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public void CreateAccount(String email, String username, String password) throws Exception{      
        
        // Checking email
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if(!matcher.find()) throw new Exception("Invalid email");
        
        // Verifying not already in database
        Account test = accountRepository.findByEmail(email);
        if(test != null) throw new Exception("Already in database");
   
        Account acc = new Account();
        acc.setEmail(email);
        acc.setUsername(username);
        acc.setPassword(password);      
        accountRepository.save(acc);
    }
    
    public void EditAccount(String email, String username, String password, String profilePicUrl){
        Account acc = accountRepository.findByEmail(email);
        acc.setUsername(username);
        acc.setPassword(password);
        acc.setProfilePicUrl(profilePicUrl);
        accountRepository.save(acc);
    }
    
    public Account getAccount(String email) {
      Account account = accountRepository.findByEmail(email);
      return account;
    }
    
    public Set<Account> getMates(String email) throws Exception{
      Account acc = accountRepository.findByEmail(email);
      if(acc == null) throw new Exception("Account doesn't exist");
      Set<Account> mates = acc.getMates();
      return mates;
    }

    public void addBook(Account acc, Book b, int bookNr) {
    	
    }
    public void removeBook(Account acc, int bookNr){ 
    	
    }
}
