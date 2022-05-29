package aiss.model.resources;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;

import aiss.model.books.*;

public class BookTest {

    @Test
	public void getAllBooksTest() throws UnsupportedEncodingException {
    	BookResource bR = new BookResource();
		List<Book> books = bR.getAllBooks();
		
		assertNotNull("The search returned null", books);
		assertEquals("There are 33 books", 33, books.size());
		
		System.out.println("There are " + books.size() + " books");
		
		//Print books data
		for(Book item : books) {
			System.out.println("********** Book title: " + item.getTitle() + " **********");
            System.out.println("ISBN: " + item.getIsbn());
            System.out.println("Price: " + item.getPrice());

		}
	
	}
    
    @Test
	public void getDramaBooksTest() throws UnsupportedEncodingException {
    	BookResource bR = new BookResource();
		List<Book> books = bR.getBooksByGenre("Drama");
		
		assertNotNull("The search returned null", books);
		assertEquals("There are 3 books", 3, books.size());
		assertEquals("The first book is 50 Sombras de Grey", "50 Sombras de Grey", books.get(0).getTitle());
		assertEquals("The second book is 50 Sombras Liberadas", "50 Sombras Liberadas", books.get(1).getTitle());
		assertEquals("The third book is 50 Sombras mas Oscuras", "50 Sombras mas Oscuras", books.get(2).getTitle());
		}

    
    @Test
	public void getAuthorBooksTest() throws UnsupportedEncodingException {
    	BookResource bR = new BookResource();
		List<Book> books = bR.getBooksByAuthor("Dumas");
		
		assertNotNull("The search returned null", books);
		assertEquals("There are 2 books", 2, books.size());
		
		Book book1 = books.get(0);
		Book book2 = books.get(1);
		
		assertEquals("The first book is El conde de Montecristo" , "El conde de Montecristo", book1.getTitle());
		assertEquals("The second book is Los tres mosqueteros", "Los tres mosqueteros", book2.getTitle());
		assertEquals("The ISBN of El conde de Montecristo is 6435174693", "6435174693", book1.getIsbn());
		assertEquals("The ISBN of Los tres mosqueteros is 2465174693", "2465174693", book2.getIsbn());
		assertTrue("The price of El conde de Montecristo is 14.89", book1.getPrice() == 14.89);
		assertTrue("The price of Los tres mosqueteros is 15.15", book2.getPrice() == 15.15);
    	}

}
