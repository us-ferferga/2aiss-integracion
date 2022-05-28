package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import aiss.model.books.*;


import org.restlet.resource.ClientResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookResource {
	private static final Logger log = Logger.getLogger(BookResource.class.getName());


	private List<Book> client(String uri) throws UnsupportedEncodingException{
        List<Book> books = new ArrayList<Book>();
		log.log(Level.INFO, "URI: " + uri);

        try {
        	ClientResource cr = new ClientResource(uri);
        	String bookSearch = cr.get(String.class);
        	ObjectMapper objectMapper = new ObjectMapper();
        	books = objectMapper.readValue(
        	        bookSearch, 
        	        new TypeReference<List<Book>>(){}); 
		} catch (Exception e) {
		}
        return books;
	}
	
	public List<Book> getAllBooks() throws UnsupportedEncodingException{
        String uri = "https://book-api-aiss.ey.r.appspot.com/api/books";
        return client(uri);
	}

	
	public List<Book> getBooksByGenre(String genre) throws UnsupportedEncodingException{
        String uri = "https://book-api-aiss.ey.r.appspot.com/api/books/genre/" + genre;
        return client(uri);
	}

	public List<Book> getBooksByAuthor(String author) throws UnsupportedEncodingException{
        String uri = "https://book-api-aiss.ey.r.appspot.com/api/books/author/" + author;
        return client(uri);
	}

	
}
