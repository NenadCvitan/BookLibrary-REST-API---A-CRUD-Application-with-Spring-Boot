package com.example.crud.ControllerTests;

import com.example.crud.Controllers.BookController;
import com.example.crud.Entitys.Book;
import com.example.crud.Services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class BookControllerTest {

    @Mock
    private BookService bookService; // Mocked BookService

    @InjectMocks
    private BookController bookController; // Controller with mocked service

    private Book sampleBook; // Sample book for testing

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        sampleBook = new Book(1, "Sample Title", "Author Name", LocalDate.now(), "Fiction",
                "1234567890", 300, "Sample Publisher", "English",
                "Sample description", 19.99, "cover.jpg", "In Stock");
    }

    @Test
    void shouldAddBook() {
        when(bookService.addBook(any(Book.class))).thenReturn(sampleBook); // Mock the addBook method
        ResponseEntity<Book> response = bookController.addBook(sampleBook); // Call the controller method
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Check response status
        verify(bookService, times(1)).addBook(sampleBook); // Verify the method was called
    }

    @Test
    void shouldFindBookById() {
        when(bookService.findById(1)).thenReturn(Optional.of(sampleBook)); // Mock book retrieval by ID
        ResponseEntity<Book> response = bookController.findById(1); // Call the controller method
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check response status
        verify(bookService, times(1)).findById(1); // Verify the method was called
    }

    @Test
    void shouldReturnNotFoundIfBookNotFound() {
        when(bookService.findById(1)).thenReturn(Optional.empty()); // Mock not found case
        ResponseEntity<Book> response = bookController.findById(1); // Call the controller method
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Check response status
        verify(bookService, times(1)).findById(1); // Verify the method was called
    }

    @Test
    void shouldGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(List.of(sampleBook)); // Mock getting all books
        ResponseEntity<List<Book>> response = bookController.getAllBooks(); // Call the controller method
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check response status
        verify(bookService, times(1)).getAllBooks(); // Verify the method was called
    }

    @Test
    void shouldSearchBooks() {
        when(bookService.searchBooks("Sample Title", null, null, null))
                .thenReturn(List.of(sampleBook)); // Mock search results
        ResponseEntity<List<Book>> response = bookController.searchBooks("Sample Title", null, null, null); // Call the controller method
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check response status
        verify(bookService, times(1)).searchBooks("Sample Title", null, null, null); // Verify the method was called
    }

    @Test
    void shouldReturnNotFoundIfNoBooksFoundInSearch() {
        when(bookService.searchBooks("Nonexistent Title", null, null, null))
                .thenReturn(List.of()); // Mock no search results
        ResponseEntity<List<Book>> response = bookController.searchBooks("Nonexistent Title", null, null, null); // Call the controller method
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Check response status
        verify(bookService, times(1)).searchBooks("Nonexistent Title", null, null, null); // Verify the method was called
    }

    @Test
    void shouldDeleteBook() {
        doNothing().when(bookService).deleteById(1); // Mock delete method
        ResponseEntity<Void> response = bookController.deleteBook(1); // Call the controller method
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Check response status
        verify(bookService, times(1)).deleteById(1); // Verify the method was called
    }

}
