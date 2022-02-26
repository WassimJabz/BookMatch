package bluescorpions.BookMatchBackend.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.ChatMessage;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {
	    
      public Optional<ChatMessage> findById(String id);
	    
	  public List<ChatMessage> findBySenderAndReceiver (Account sender, Account Receiver);
	    
}
