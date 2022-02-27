package bluescorpions.BookMatchBackend.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.Author;
import bluescorpions.BookMatchBackend.model.Book;
import bluescorpions.BookMatchBackend.services.AccountService;
import bluescorpions.BookMatchBackend.services.AuthorService;
import bluescorpions.BookMatchBackend.services.BookService;

@CrossOrigin
@RestController
public class AccountController {

  @Autowired 
  private AccountService accountService;
  
  @Autowired 
  private BookService bookService;
  
  @Autowired 
  private AuthorService authorService;
  
  @GetMapping("/registration")
  public ResponseEntity<Void> register(@RequestParam Map<String, String> params){
    
    String email = params.get("email");
    String username = params.get("username");
    String password = params.get("password");
    
    try {
      accountService.CreateAccount(email, username, password);
    }
    catch(Exception e){
      return ResponseEntity.badRequest().build();
    }
    
    return ResponseEntity.ok().build();
  }
  
  @GetMapping("/login")
  public ResponseEntity<Void> login(@RequestParam Map<String, String> params, HttpServletResponse response){
    
    String email = params.get("email");
    String password = params.get("password");
    System.out.println("--------email " + email);
    Account account = accountService.getAccount(email);
    
    if(account != null && account.getPassword().equals(password)) {
        Cookie cookie = new Cookie("email", email);
        response.addCookie(cookie);
      return ResponseEntity.ok().build();
    }
    
    return ResponseEntity.badRequest().build();
  }
  
  @GetMapping("/getMates")
  public ResponseEntity<Set<Account>> fetchMates(@RequestParam Map<String, String> params){
    
    String email = params.get("email");
    
    Account account = accountService.getAccount(email);
    
    Set<Account> mates = account.getMates();
    
    ResponseEntity<Set<Account>> entity = new ResponseEntity(mates, HttpStatus.OK);
    
    return entity;
  }
  
  @GetMapping("/addMate")
  public ResponseEntity<Set<Account>> addMate(@CookieValue(name = "email") String email, @RequestParam Map<String, String> params){
    
    Account account = accountService.getAccount(email);
    
    Set<Account> mates = account.getMates();
    
    ResponseEntity<Set<Account>> entity = new ResponseEntity(mates, HttpStatus.OK);
    
    return entity;
  }
  
  @GetMapping("/getBooks")
  public ResponseEntity<Set<Book>> getBooks(@CookieValue(name = "email") String email){
    
    Account acc = accountService.getAccount(email);
    
    Set<Book> books = acc.getBooks();
    
    ResponseEntity<Set<Book>> entity = new ResponseEntity(books, HttpStatus.OK);
    
    return entity;
    
  }
  
  @GetMapping("/setBooks")
  public ResponseEntity<Void> setBooks(@CookieValue(name = "email") String email, @RequestParam Map<String, String> params){

    String book1info = params.get("book1");
    String book2info = params.get("book2");
    String book3info = params.get("book3");
    
    String[] book1arr = (book1info == null)? null : book1info.split("|");
    String[] book2arr = (book2info == null)? null : book2info.split("|");
    String[] book3arr = (book3info == null)? null : book3info.split("|");
    
    Set<Book> bookSet = new HashSet();
    
    try {
      
      if (book1arr != null) {
        String isbn = book1arr[0];
        String title = book1arr[1];
        String[] authors = book1arr[2].split(",");
        String subject = book1arr[3];

        Set<Author> authorSet = new HashSet();

        for (String a : authors)
          authorSet.add(authorService.createAuthor(a));


        Book book = bookService.CreateBook(isbn, title, authorSet, subject);


        bookSet.add(book);

      }
      
      if (book2arr != null) {
        String isbn = book2arr[0];
        String title = book2arr[1];
        String[] authors = book2arr[2].split(",");
        String subject = book2arr[3];

        Set<Author> authorSet = new HashSet();

        for (String a : authors)
          authorSet.add(authorService.createAuthor(a));


        Book book = bookService.CreateBook(isbn, title, authorSet, subject);


        bookSet.add(book);

      }
      
      if (book3arr != null) {
        String isbn = book3arr[0];
        String title = book3arr[1];
        String[] authors = book3arr[2].split(",");
        String subject = book3arr[3];

        Set<Author> authorSet = new HashSet();

        for (String a : authors)
          authorSet.add(authorService.createAuthor(a));


        Book book = bookService.CreateBook(isbn, title, authorSet, subject);


        bookSet.add(book);

      }
      
      accountService.overrideBooks(email, bookSet);
      
    }
    catch(Exception e) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok().build();
  }
  
}
