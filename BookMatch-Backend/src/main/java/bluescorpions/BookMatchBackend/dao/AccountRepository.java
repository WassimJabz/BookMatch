package bluescorpions.BookMatchBackend.dao;

import org.springframework.data.repository.CrudRepository;

import bluescorpions.BookMatchBackend.model.Account;

public interface AccountRepository extends CrudRepository<Account, String>{

    public Account findByEmail(String email);
}
