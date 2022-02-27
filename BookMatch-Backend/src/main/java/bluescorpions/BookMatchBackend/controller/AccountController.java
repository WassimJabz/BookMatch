package bluescorpions.BookMatchBackend.controller;

import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.services.AccountService;

public class AccountController {

  @Autowired 
  private AccountService accountService;
  
  @GetMapping("/registration")
  public ResponseEntity<Void> register(@RequestParam Map<String, String> params){
    
    String email = params.get("email");
    String username = params.get("email");
    String password = params.get("password");
    
    try {
      accountService.CreateAccount(email, username, password);
    }
    catch(Exception e){
      return ResponseEntity.badRequest().build();
    }
    
    return ResponseEntity.ok().build();
  }
  
  @GetMapping("/login/{email}/{password}")
  public ResponseEntity<Void> login(@PathVariable String email, @PathVariable String password){
    
    
    Account account = accountService.getAccount(email);
    
    if(account != null && account.getPassword().equals(password)) {
      return ResponseEntity.ok().build();
    }
    
    return ResponseEntity.badRequest().build();
  }
  
  @GetMapping("/login/{email}")
  public ResponseEntity<Set<Account>> fetchMates(@PathVariable String email){
    
    Account account = accountService.getAccount(email);
    
    Set<Account> mates = account.getMates();
    
    ResponseEntity<Set<Account>> entity = new ResponseEntity(mates, HttpStatus.OK);
    
    return entity;
  }
  
}
