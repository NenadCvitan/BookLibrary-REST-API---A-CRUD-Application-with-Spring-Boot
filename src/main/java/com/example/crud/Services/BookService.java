package com.example.crud.Services;

import com.example.crud.Entitys.Book;
import com.example.crud.Repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // Marks this class as a service to handle business logic
public class BookService {

    private final BookRepository bookRepository; // The repository for interacting with the database

    @Autowired // Constructor injection to inject the repository
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Adds a new book to the database
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Finds a book by its ID
    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(bookRepository.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + id + " not found"));
    }

    // Retrieves all books from the database
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Finds a book by its ISBN
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(bookRepository.findByIsbn(isbn))
                .orElseThrow(() -> new IllegalArgumentException("Book with ISBN " + isbn + " not found"));
    }

    // Searches for books by different attributes like title, author, publisher, or price
    public List<Book> searchBooks(String title, String author, String publisher, Double price) {
        if (title != null) {
            return bookRepository.findByTitle(title);
        } else if (author != null) {
            return bookRepository.findByAuthor(author);
        } else if (publisher != null) {
            return bookRepository.findByPublisher(publisher);
        } else if (price != null) {
            return bookRepository.findByPrice(price);
        }
        return bookRepository.findAll(); // If no filter, return all books
    }

    // Finds books by genre
    public List<Book> findByGenre(String genre) {
        if (genre != null) {
            return bookRepository.findByGenre(genre);
        }
        return new ArrayList<>(); // Return an empty list if no genre is provided
    }

    // Finds books by language
    public List<Book> findByLanguage(String language) {
        if (language != null) {
            return bookRepository.findByLanguage(language);
        }
        return new ArrayList<>(); // Return an empty list if no language is provided
    }

    // Finds books by availability status
    public List<Book> findByAvailability(String availability) {
        if (availability != null) {
            return bookRepository.findByAvailability(availability);
        }
        return new ArrayList<>(); // Return an empty list if no availability is provided
    }

    // Deletes a book by its ID
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    // Updates a book's details by its ID
    public Book updateBook(Integer id, Book bookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + id + " not found"));

        // Updating the book's fields with the new details
        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setPublisher(bookDetails.getPublisher());
        existingBook.setPrice(bookDetails.getPrice());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setIsbn(bookDetails.getIsbn());

        return bookRepository.save(existingBook); // Save the updated book
    }
}
