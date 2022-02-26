package bluescorpions.BookMatchBackend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bluescorpions.BookMatchBackend.model.Account;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {
	    public ChatMessage findById(String id);
	    
	    public List<ChatMessage> findBySenderAndReceiver (Account sender, Account Receiver);
	    
}
