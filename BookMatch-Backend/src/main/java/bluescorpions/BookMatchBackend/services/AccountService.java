package bluescorpions.BookMatchBackend.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import bluescorpions.BookMatchBackend.dao.AccountRepository;
import bluescorpions.BookMatchBackend.model.Account;

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
}
