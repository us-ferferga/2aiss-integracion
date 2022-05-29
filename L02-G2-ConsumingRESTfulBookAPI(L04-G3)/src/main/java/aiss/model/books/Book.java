
package aiss.model.books;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "title", "author", "genre", "publicationDate", "isbn", "sypnosis", "publisher", "price" })
@Generated("jsonschema2pojo")
public class Book {

	@JsonProperty("id")
	private String id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("author")
	private String author;
	@JsonProperty("genre")
	private String genre;
	@JsonProperty("publicationDate")
	private PublicationDate publicationDate;
	@JsonProperty("isbn")
	private String isbn;
	@JsonProperty("sypnosis")
	private String sypnosis;
	@JsonProperty("publisher")
	private String publisher;
	@JsonProperty("price")
	private Double price;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}

	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author = author;
	}

	@JsonProperty("genre")
	public String getGenre() {
		return genre;
	}

	@JsonProperty("genre")
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@JsonProperty("publicationDate")
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}

	@JsonProperty("publicationDate")
	public void setPublicationDate(PublicationDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	@JsonProperty("isbn")
	public String getIsbn() {
		return isbn;
	}

	@JsonProperty("isbn")
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@JsonProperty("sypnosis")
	public String getSypnosis() {
		return sypnosis;
	}

	@JsonProperty("sypnosis")
	public void setSypnosis(String sypnosis) {
		this.sypnosis = sypnosis;
	}

	@JsonProperty("publisher")
	public String getPublisher() {
		return publisher;
	}

	@JsonProperty("publisher")
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}

	@JsonProperty("price")
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
