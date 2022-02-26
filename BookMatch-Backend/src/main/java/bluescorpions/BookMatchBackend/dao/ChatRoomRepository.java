package bluescorpions.BookMatchBackend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bluescorpions.BookMatchBackend.model.Account;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {
	  public ChatRoom findById(String id);
	    
	  public List<ChatRoom> findByAccount1AndAccount2 (Account account1, Account account2);
}
