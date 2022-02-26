package bluescorpions.BookMatchBackend.dao;

import org.springframework.data.repository.CrudRepository;
import bluescorpions.BookMatchBackend.model.Author;

public interface AuthorRepository extends CrudRepository<Author, String>{

}
