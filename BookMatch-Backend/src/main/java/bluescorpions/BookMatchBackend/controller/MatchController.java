package bluescorpions.BookMatchBackend.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;



import bluescorpions.BookMatchBackend.dao.AccountRepository;
import bluescorpions.BookMatchBackend.model.Account;
import bluescorpions.BookMatchBackend.model.Book;

public class MatchController {
	@Autowired
	private AccountRepository accountRepository;
	
	public Account CreateMatch(Account account) {
		
		ArrayList<Account> matches;
		// If the account doesnt have books, return random match
		if (account.getBooks().size() == 0) {
			return accountRepository.findRandomAccount();
		
			// If account has 1 favorite book, return a random account that has the same 1 book
		}else if (account.getBooks().size() == 1){
			matches = (ArrayList<Account>) accountRepository.findByBooks(account.getBooks());
			// If no match was found, return a random match
			if(matches.size() == 0) {
				return accountRepository.findRandomAccount();
			}else {
				Random rand = new Random();
				return matches.get(rand.nextInt(matches.size()-1));
			}
			// If the account has 2 favorite books linked to it, return random account that has the same 2 favorite books
		}else if (account.getBooks().size() == 2) {
			matches = (ArrayList<Account>) accountRepository.findByBooks(account.getBooks());
			
			// If no match was found, check for matches with only 1 book
			if(matches.size() == 0) {
				HashSet<Book> book = new HashSet<Book>();
				for (Book b: account.getBooks()) {
					book.add(b);
					matches = (ArrayList<Account>) accountRepository.findByBooks(book);
					if(matches.size() == 0) {
						book.remove(b);
					}else {
						Random rand = new Random();
						return matches.get(rand.nextInt(matches.size()-1));
					}
				}
				return accountRepository.findRandomAccount();
			}else {
				Random rand = new Random();
				return matches.get(rand.nextInt(matches.size()-1));
			}
			// IF the account has 3 books
		}else {
			matches = (ArrayList<Account>) accountRepository.findByBooks(account.getBooks());
			if(matches.size() == 0) {
				ArrayList<Book> myBook = new ArrayList<Book>();
				ArrayList<Book> myBook2 = new ArrayList<Book>();


				for (Book b: account.getBooks()) {
					myBook.add(b);
					myBook2.add(b);
				}
				
				for(int i=0; i<account.getBooks().size(); i++) {
					HashSet<Book> book = new HashSet<Book>();
					myBook2.remove(i);
					
					for(Book b: myBook2) {
						book.add(b);
					}
					
					matches = (ArrayList<Account>) accountRepository.findByBooks(book);
					
					if(matches.size() != 0) {
						Random rand = new Random();
						return matches.get(rand.nextInt(matches.size()-1));
					}else {
						myBook2.add(i, myBook.get(i));
					}
				}
				for(Book b: myBook) {
					HashSet<Book> book = new HashSet<Book>();
					book.add(b);
					matches = (ArrayList<Account>) accountRepository.findByBooks(book);
					if(matches.size() != 0) {
						Random rand = new Random();
						return matches.get(rand.nextInt(matches.size()-1));
					}
				}
				return accountRepository.findRandomAccount();
				
			}else {
				Random rand = new Random();
				return matches.get(rand.nextInt(matches.size()-1));
			}
		}
	}
}
