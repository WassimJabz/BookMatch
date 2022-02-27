//package bluescorpions.BookMatchBackend.services;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Random;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//
//import bluescorpions.BookMatchBackend.dao.AccountRepository;
//import bluescorpions.BookMatchBackend.model.Account;
//import bluescorpions.BookMatchBackend.model.Book;
//
//public class MatchService {
//	@Autowired
//	private AccountRepository accountRepository;
//	
//	public Account CreateMatch(Account account) throws Exception {
//		// If there's only one account in the system, throw error.
//		if(accountRepository.count() <= 1) {
//			throw new Exception("There are no other mates in the system");
//		}
//		
//		ArrayList<Account> matches;
//		// If the account doesn't have books, throw error.
//		if (account.getBooks().size() == 0) {
//			throw new Exception("The account doesn't have preferred books.");
//			
//		// If account has 1 favorite book, add a random mate that has the same book
//		}else if (account.getBooks().size() == 1){
//			matches = (ArrayList<Account>) accountRepository.findByBooks(account.getBooks());
//			// If no match was found, return add a random mate
//			if(matches.size() == 0) {
//				Account match = accountRepository.findRandomAccount();
//				account.getMates().add(match);
//				return match;
//			}else {
//				Random rand = new Random();
//				Account match = matches.get(rand.nextInt(matches.size()-1));
//				account.getMates().add(match);
//				return match;
//			}
//			// If the account has 2 favorite books linked to it, match it with random account that has the same 2 favorite books
//		}else if (account.getBooks().size() == 2) {
//			matches = (ArrayList<Account>) accountRepository.findByBooks(account.getBooks());
//			
//			// If no match was found with these 2 books, check for matches with only 1 book (out of the two favorite ones)
//			if(matches.size() == 0) {
//				HashSet<Book> book = new HashSet<Book>();
//				for (Book b: account.getBooks()) {
//					book.add(b);
//					matches = (ArrayList<Account>) accountRepository.findByBooks(book);
//					if(matches.size() == 0) {
//						book.remove(b);
//					}else {
//						Random rand = new Random();
//						Account match = matches.get(rand.nextInt(matches.size()-1));
//						account.getMates().add(match);
//						return match;
//					}
//				}
//				Account match = accountRepository.findRandomAccount();
//				account.getMates().add(match);
//				return match;
//			}else {
//				Random rand = new Random();
//				Account match = matches.get(rand.nextInt(matches.size()-1));
//				account.getMates().add(match);
//				return match;
//			}
//			// IF the account has 3 books
//		}else {
//			matches = (ArrayList<Account>) accountRepository.findByBooks(account.getBooks());
//			
//			//If no match with these 3 books, try with 2 books
//			if(matches.size() == 0) {
//				ArrayList<Book> myBook = new ArrayList<Book>();
//				ArrayList<Book> myBook2 = new ArrayList<Book>();
//
//
//				for (Book b: account.getBooks()) {
//					myBook.add(b);
//					myBook2.add(b);
//				}
//				
//				// Try all possible 2 book combination from the set of 3 books associated with the account.
//				for(int i=0; i<account.getBooks().size(); i++) {
//					HashSet<Book> book = new HashSet<Book>();
//					myBook2.remove(i);
//					
//					for(Book b: myBook2) {
//						book.add(b);
//					}
//					
//					matches = (ArrayList<Account>) accountRepository.findByBooks(book);
//					
//					if(matches.size() != 0) {
//						Random rand = new Random();
//						Account match = matches.get(rand.nextInt(matches.size()-1));
//						account.getMates().add(match);
//						return match;
//					}else {
//						myBook2.add(i, myBook.get(i));
//					}
//				}
//				
//				for(Book b: myBook) {
//					HashSet<Book> book = new HashSet<Book>();
//					book.add(b);
//					matches = (ArrayList<Account>) accountRepository.findByBooks(book);
//					if(matches.size() != 0) {
//						Random rand = new Random();
//						Account match = matches.get(rand.nextInt(matches.size()-1));
//						account.getMates().add(match);
//						return match;
//					}
//				}
//				
//				Account match = accountRepository.findRandomAccount();
//				account.getMates().add(match);
//				return match;
//				
//			}else {
//				Random rand = new Random();
//				Account match = matches.get(rand.nextInt(matches.size()-1));
//				account.getMates().add(match);
//				return match;
//			}
//		}
//	}
//}
