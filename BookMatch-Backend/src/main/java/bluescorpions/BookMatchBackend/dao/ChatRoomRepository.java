package bluescorpions.BookMatchBackend.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.ChatRoom;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {
	 
      public Optional<ChatRoom> findById(String id);
	    
	  public List<ChatRoom> findByAccount1AndAccount2 (Account account1, Account account2);
}
