package bluescorpions.BookMatchBackend;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class BookMatchBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMatchBackendApplication.class, args);
	}

	@RequestMapping("/")
  	public String greeting(){
    return "Hello world!";
  }

}
