package com.example.crud.Repos;

import com.example.crud.Entitys.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Marks this interface as a Spring Data JPA repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Finds a book by its ID
    Optional<Book> findById(Integer id);

    // Finds a book by its ISBN
    Optional<Book> findByIsbn(String Isbn);

    // Methods to find multiple books based on different attributes

    // Finds all books in the database
    List<Book> findAll();

    // Finds books by their title
    List<Book> findByTitle(String title);

    // Finds books by the author's name
    List<Book> findByAuthor(String author);

    // Finds books by their publisher's name
    List<Book> findByPublisher(String publisher);

    // Finds books by price
    List<Book> findByPrice(Double price);

    // Finds books by their genre
    List<Book> findByGenre(String genre);

    // Finds books by their language
    List<Book> findByLanguage(String language);

    // Finds books by availability status
    List<Book> findByAvailability(String availability);

}
