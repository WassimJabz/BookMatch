package bluescorpions.BookMatchBackend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import bluescorpions.BookMatchBackend.model.WebSocketChatMessage;
import BookMatchBackend.dao.*;
@RestController
public class AccountController {
    AccountRepository accountRepository = new AccountRepository();
    public void CreateAccount(String email, String username, String password){
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

    }
}
