package com.example.crud.Controllers;

import com.example.crud.Entitys.Book;
import com.example.crud.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books") // Defines the base path for all the endpoints in this controller
public class BookController {

    private final BookService bookService; // Service instance that provides the business logic for books

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService; // Injecting the BookService via the constructor
    }

    // Post Calls

    @PostMapping("/addBook") // Endpoint to add a new book
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book); // Saves the new book
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED); // Returns the saved book with CREATED status
    }

    // Get Calls

    @GetMapping("/{id}") // Endpoint to get a book by its ID
    public ResponseEntity<Book> findById(@PathVariable Integer id) {
        return bookService.findById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK)) // Returns the book if found
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Returns NOT_FOUND if the book is not found
    }

    @GetMapping("/{isbn}") // Endpoint to get a book by its ISBN
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK)) // Returns the book if found
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Returns NOT_FOUND if the book is not found
    }

    @GetMapping("/getAll") // Endpoint to get all books
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns NO_CONTENT if no books are found
        }
        return new ResponseEntity<>(books, HttpStatus.OK); // Returns all books with OK status
    }

    @GetMapping("/search") // Endpoint to search books by optional parameters
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String publisher,
                                                  @RequestParam(required = false) Double price) {
        List<Book> books = bookService.searchBooks(title, author, publisher, price);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns NOT_FOUND if no books match the search criteria
        }
        return new ResponseEntity<>(books, HttpStatus.OK); // Returns the books that match the search criteria
    }

    @GetMapping("/{genre}") // Endpoint to get books by genre
    public ResponseEntity<List<Book>> findByGenre(@PathVariable String genre) {
        List<Book> books = bookService.findByGenre(genre);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns NO_CONTENT if no books match the genre
        }
        return new ResponseEntity<>(books, HttpStatus.OK); // Returns the books matching the genre
    }

    @GetMapping("/{language}") // Endpoint to get books by language
    public ResponseEntity<List<Book>> findByLanguage(@PathVariable String language) {
        List<Book> books = bookService.findByLanguage(language);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns NO_CONTENT if no books match the language
        }
        return new ResponseEntity<>(books, HttpStatus.OK); // Returns the books matching the language
    }

    @GetMapping("/{availability}") // Endpoint to get books by availability
    public ResponseEntity<List<Book>> findByAvailability(@PathVariable String availability) {
        List<Book> books = bookService.findByAvailability(availability);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns NO_CONTENT if no books match the availability status
        }
        return new ResponseEntity<>(books, HttpStatus.OK); // Returns the books matching the availability status
    }

    // Delete Calls

    @DeleteMapping("/{id}") // Endpoint to delete a book by its ID
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteById(id); // Deletes the book by its ID
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns NOT_FOUND (or use NO_CONTENT) after deletion
    }

    // Put Calls / Update book

    @PutMapping("/{id}") // Endpoint to update a book's details
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails); // Updates the book with new details
        return new ResponseEntity<>(updatedBook, HttpStatus.OK); // Returns the updated book with OK status
    }

}
