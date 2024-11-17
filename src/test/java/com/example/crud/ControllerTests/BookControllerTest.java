package com.example.crud.ControllerTests;

import com.example.crud.Controllers.BookController;
import com.example.crud.Entitys.Book;
import com.example.crud.Services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleBook = new Book(1, "Sample Title", "Author Name", LocalDate.now(), "Fiction",
                "1234567890", 300, "Sample Publisher", "English",
                "Sample description", 19.99, "cover.jpg", "In Stock");
    }

    @Test
    void shouldAddBook() {
        when(bookService.addBook(any(Book.class))).thenReturn(sampleBook);
        ResponseEntity<Book> response = bookController.addBook(sampleBook);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Title", response.getBody().getTitle());
        verify(bookService, times(1)).addBook(sampleBook);
    }

    @Test
    void shouldFindBookById() {
        when(bookService.findById(1)).thenReturn(Optional.of(sampleBook));
        ResponseEntity<Book> response = bookController.findById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Title", response.getBody().getTitle());
        verify(bookService, times(1)).findById(1);
    }

    @Test
    void shouldReturnNotFoundIfBookNotFound() {
        when(bookService.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Book> response = bookController.findById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).findById(1);
    }

    @Test
    void shouldGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(List.of(sampleBook));
        ResponseEntity<List<Book>> response = bookController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Sample Title", response.getBody().get(0).getTitle());
        verify(bookService, times(1)).getAllBooks();
    }


    @Test
    void shouldSearchBooks() {
        when(bookService.searchBooks("Sample Title", null, null, null))
                .thenReturn(List.of(sampleBook));
        ResponseEntity<List<Book>> response = bookController.searchBooks("Sample Title", null, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(bookService, times(1)).searchBooks("Sample Title", null, null, null);
    }

    @Test
    void shouldReturnNotFoundIfNoBooksFoundInSearch() {
        when(bookService.searchBooks("Nonexistent Title", null, null, null))
                .thenReturn(List.of());
        ResponseEntity<List<Book>> response = bookController.searchBooks("Nonexistent Title", null, null, null);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).searchBooks("Nonexistent Title", null, null, null);
    }

    @Test
    void shouldDeleteBook() {
        doNothing().when(bookService).deleteById(1);
        ResponseEntity<Void> response = bookController.deleteBook(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).deleteById(1);
    }
}
