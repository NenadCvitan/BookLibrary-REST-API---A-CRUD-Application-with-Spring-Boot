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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

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
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook);
        Book savedBook = bookService.addBook(sampleBook);
        assertNotNull(savedBook);
        assertEquals("Sample Title", savedBook.getTitle());
        verify(bookRepository, times(1)).save(sampleBook);
    }

    @Test
    void shouldFindBookById() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(sampleBook));
        Optional<Book> foundBook = bookService.findById(1);
        assertTrue(foundBook.isPresent());
        assertEquals("Sample Title", foundBook.get().getTitle());
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void shouldReturnEmptyIfBookNotFound() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        Optional<Book> foundBook = bookService.findById(1);
        assertFalse(foundBook.isPresent());
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void shouldDeleteBook() {
        doNothing().when(bookRepository).deleteById(1);
        bookService.deleteById(1);
        verify(bookRepository, times(1)).deleteById(1);
    }

    @Test
    void shouldUpdateBook() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(sampleBook));
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook);
        Book updatedBookDetails = new Book();
        updatedBookDetails.setTitle("Updated Title");
        Book updatedBook = bookService.updateBook(1, updatedBookDetails);
        assertNotNull(updatedBook);
        assertEquals("Updated Title", updatedBook.getTitle());
        verify(bookRepository, times(1)).findById(1);
        verify(bookRepository, times(1)).save(sampleBook);
    }

    @Test
    void shouldGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(sampleBook));
        List<Book> allBooks = bookService.getAllBooks();
        assertEquals(1, allBooks.size());
        assertEquals("Sample Title", allBooks.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }
}
