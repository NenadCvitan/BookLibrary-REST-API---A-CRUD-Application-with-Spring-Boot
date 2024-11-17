package com.example.crud.ServicesTests;

import com.example.crud.Entitys.Book;
import com.example.crud.Repos.BookRepository;
import com.example.crud.Services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock // Mock the BookRepository to simulate database operations
    private BookRepository bookRepository;

    @InjectMocks // Inject the mocked repository into the BookService
    private BookService bookService;

    private Book sampleBook; // Sample book object for testing

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        // Create a sample book to use in tests
        sampleBook = new Book(1, "Sample Title", "Author Name", LocalDate.now(), "Fiction",
                "1234567890", 300, "Sample Publisher", "English",
                "Sample description", 19.99, "cover.jpg", "In Stock");
    }

    // Test for adding a book
    @Test
    void shouldAddBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook); // Mock save behavior
        Book savedBook = bookService.addBook(sampleBook);
        assertNotNull(savedBook); // Ensure the saved book is not null
        assertEquals("Sample Title", savedBook.getTitle()); // Verify the title is correct
        verify(bookRepository, times(1)).save(sampleBook); // Verify that save was called once
    }

    // Test for finding a book by ID
    @Test
    void shouldFindBookById() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(sampleBook)); // Mock findById behavior
        Optional<Book> foundBook = bookService.findById(1);
        assertTrue(foundBook.isPresent()); // Verify the book is present
        assertEquals("Sample Title", foundBook.get().getTitle()); // Verify the title is correct
        verify(bookRepository, times(1)).findById(1); // Verify findById was called once
    }

    // Test for when a book is not found by ID
    @Test
    void shouldReturnEmptyIfBookNotFound() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty()); // Mock empty result for findById
        Optional<Book> foundBook = bookService.findById(1);
        assertFalse(foundBook.isPresent()); // Verify the book is not found
        verify(bookRepository, times(1)).findById(1); // Verify findById was called once
    }

    // Test for deleting a book by ID
    @Test
    void shouldDeleteBook() {
        doNothing().when(bookRepository).deleteById(1); // Mock deleteById behavior
        bookService.deleteById(1);
        verify(bookRepository, times(1)).deleteById(1); // Verify deleteById was called once
    }

    // Test for updating a book
    @Test
    void shouldUpdateBook() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(sampleBook)); // Mock findById behavior
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook); // Mock save behavior
        Book updatedBookDetails = new Book();
        updatedBookDetails.setTitle("Updated Title"); // Update the book title
        Book updatedBook = bookService.updateBook(1, updatedBookDetails);
        assertNotNull(updatedBook); // Ensure the updated book is not null
        assertEquals("Updated Title", updatedBook.getTitle()); // Verify the updated title
        verify(bookRepository, times(1)).findById(1); // Verify findById was called once
        verify(bookRepository, times(1)).save(sampleBook); // Verify save was called once
    }

    // Test for retrieving all books
    @Test
    void shouldGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(sampleBook)); // Mock findAll behavior
        List<Book> allBooks = bookService.getAllBooks();
        assertEquals(1, allBooks.size()); // Ensure there's one book in the list
        assertEquals("Sample Title", allBooks.get(0).getTitle()); // Verify the title of the first book
        verify(bookRepository, times(1)).findAll(); // Verify findAll was called once
    }

    // Test for finding books by genre
    @Test
    void testFindByGenre_ReturnsBooks() {
        String genre = "Science Fiction";
        Book book = new Book(1, "Dune", "Frank Herbert", LocalDate.of(1965, 8, 1), genre, "123456789", 412, "Chilton Books", "English", "A science fiction classic.", 19.99, "path/to/cover.jpg", "Available");
        List<Book> books = Arrays.asList(book);
        when(bookRepository.findByGenre(genre)).thenReturn(books); // Mock findByGenre behavior

        List<Book> result = bookService.findByGenre(genre); // Call the service method

        assertEquals(1, result.size()); // Verify one book is returned
        assertEquals(genre, result.get(0).getGenre()); // Verify the genre is correct
    }

    // Test for finding books by language
    @Test
    void testFindByLanguage_ReturnsBooks() {
        String language = "English";
        Book book = new Book(1, "Dune", "Frank Herbert", LocalDate.of(1965, 8, 1), "Science Fiction", "123456789", 412, "Chilton Books", language, "A science fiction classic.", 19.99, "path/to/cover.jpg", "Available");
        List<Book> books = Arrays.asList(book);
        when(bookRepository.findByLanguage(language)).thenReturn(books); // Mock findByLanguage behavior

        List<Book> result = bookService.findByLanguage(language); // Call the service method

        assertEquals(1, result.size()); // Verify one book is returned
        assertEquals(language, result.get(0).getLanguage()); // Verify the language is correct
    }

    // Test for finding books by availability
    @Test
    void testFindByAvailability_ReturnsBooks() {
        String availability = "Available";
        Book book = new Book(1, "Dune", "Frank Herbert", LocalDate.of(1965, 8, 1), "Science Fiction", "123456789", 412, "Chilton Books", "English", "A science fiction classic.", 19.99, "path/to/cover.jpg", availability);
        List<Book> books = Arrays.asList(book);
        when(bookRepository.findByAvailability(availability)).thenReturn(books); // Mock findByAvailability behavior

        List<Book> result = bookService.findByAvailability(availability); // Call the service method

        assertEquals(1, result.size()); // Verify one book is returned
        assertEquals(availability, result.get(0).getAvailability()); // Verify the availability is correct
    }
}
