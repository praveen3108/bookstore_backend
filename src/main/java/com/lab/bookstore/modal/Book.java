package com.lab.bookstore.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {
	

	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE) private long id;
	  @Column(name = "title") private String title;
	  @Column(name = "author") private String author;
	  @Column(name = "publications") private String publications;
      public Book() {
		  
	  }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublications() {
		return publications;
	}
	public void setPublications(String publications) {
		this.publications = publications;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publications=" + publications + "]";
	}
	public Book(long id, String title, String author, String publications) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publications = publications;
	}
	  
      
}
