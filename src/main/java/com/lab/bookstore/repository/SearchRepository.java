package com.lab.bookstore.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lab.bookstore.modal.Book;

public interface SearchRepository extends PagingAndSortingRepository<Book,Long>{
	@Query("SELECT b FROM Book b WHERE " +
            "b.title LIKE CONCAT('%',:query, '%')" +
            "Or b.author LIKE CONCAT('%', :query, '%')" +
            "Or b.publications LIKE CONCAT('%', :query, '%')")
    List<Book> searchBooks(String query,Pageable pageable);

    List<Book> searchByTitle(String query,Pageable pageable);

    List<Book> searchByPublications(String query,Pageable pageable);

    List<Book> searchByAuthor(String query,Pageable pageable);
}
