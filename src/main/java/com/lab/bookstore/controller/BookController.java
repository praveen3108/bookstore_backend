package com.lab.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab.bookstore.modal.Book;
import com.lab.bookstore.repository.BookRepository;
import com.lab.bookstore.repository.SearchRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	SearchRepository searchRepo;
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			List<Book> book = new ArrayList<Book>();
			PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("title").ascending());
			
			searchRepo.findAll(pageRequest).forEach(book::add);
			if (book.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book bookdetails) {
		try {
			Book _book = bookRepo.save(new Book(bookdetails.getId(),bookdetails.getTitle(),bookdetails.getAuthor(),bookdetails.getPublications()));
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/deleteBook/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		try {
			bookRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getBook/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
		System.out.println(id);
		List<Book> bookData = bookRepo.findById(id);
       System.out.println(bookData.size());
		if (bookData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<>(bookData.get(0), HttpStatus.OK);
		}
	}
	
	@PostMapping("/updateBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book bookdetailsupdate) {
		List<Book> bookdetails = bookRepo.findById(id);

		if (bookdetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Book bookData = bookdetails.get(0);
			//bookData.setId(bookdetailsupdate.getId());
			bookData.setTitle(bookdetailsupdate.getTitle());
			bookData.setAuthor(bookdetailsupdate.getAuthor());
			bookData.setPublications(bookdetailsupdate.getPublications());
			return new ResponseEntity<>(bookRepo.save(bookData), HttpStatus.OK);
		}
	}
	@GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam("query") String query,@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
		
		 PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("title").ascending());
		List<Book> bookData = searchRepo.searchBooks(query,pageRequest);

		if (bookData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		}
    }
	@GetMapping("/searchByTitle")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam("query") String query,@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
		 PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("title").ascending());
		List<Book> bookData = searchRepo.searchByTitle(query,pageRequest);

		if (bookData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		}
    }
	@GetMapping("/searchByPublications")
    public ResponseEntity<List<Book>> searchByPublications(@RequestParam("query") String query,@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("title").ascending());
		List<Book> bookData = searchRepo.searchByPublications(query,pageRequest);

		if (bookData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		}
    }
	@GetMapping("/searchByAuthor")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestParam("query") String query,@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("title").ascending());
		List<Book> bookData = searchRepo.searchByAuthor(query,pageRequest);

		if (bookData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		}
    }
}
	


