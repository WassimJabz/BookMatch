package bluescorpions.BookMatchBackend.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.Book;

public interface AccountRepository extends CrudRepository<Account, String>{

    public Account findByEmail(String email);
    
    public List<Account> findByBooks(Set<Book> books);
    
    @Query("SELECT column FROM Account && ORDER BY RAND() && LIMIT 1")
    Account findRandomAccount();
}
