package bluescorpions.BookMatchBackend.dao;

import org.springframework.data.repository.CrudRepository;

import bluescorpions.BookMatchBackend.model.Book;

public interface BookRepository extends CrudRepository<Book, String> {

	Book findByIsbn(String isbn);
	
}
